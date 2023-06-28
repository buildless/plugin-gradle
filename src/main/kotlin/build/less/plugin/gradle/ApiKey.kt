/*
 * Copyright (c) 2023 Elide Ventures, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License.
 */

@file:Suppress("unused")

package build.less.plugin.gradle

import build.less.plugin.gradle.err.InvalidConfiguration
import java.util.SortedSet

/**
 * Describes a checked Buildless API key, which also carries metadata with it about the key. The key itself can be
 * accessed via the [key] getter.
 *
 * ## Usage examples
 *
 * Use this value class when constructing a well-typed API key during plugin configuration. For example:
 *
 * ```kotlin
 * import build.less.plugin.gradle.*
 * import build.less.plugin.gradle.settings.*
 *
 * plugin {
 *   id("build.less") version "..."
 * }
 *
 * buildless {
 *   apiKey(ApiKey.of("..."))
 * }
 *
 * // or, to enforce only `ORG`-type API keys:
 * buildless {
 *   apiKey(ApiKey.of("...", type = SubjectType.ORG))
 * }
 * ```
 *
 * ## About API keys in Buildless
 *
 * API keys are issued at the **org** and **user** levels. When a user is added to a Buildless org, they gain permission
 * to access that org tenant's data via their own personal keys.
 *
 * When you use an org key, no specific user is attached to the request. This is useful for systems like CI which don't
 * run as any specific developer.
 *
 * ### Key types
 *
 * There are two **key types** in Buildless: the **main** API key, and the **events** API key. Users and org tenants
 * both get each kind of key. When configuring the Buildless plugin for Gradle, you should always use your **main** key.
 *
 * ### API key checks
 *
 * The API key is checked for basic structural validity, and enforced against the type provided by the user, if any. If
 * the key fails these checks, an error is thrown which halts the build.
 *
 * If it is preferable to only perform cursory checks at runtime and allow the build to proceed in most cases, use any
 * of the environment variable, system/Gradle properties, or configuration file methods to specify your keys. In this
 * case, merely withholding the keys will gracefully skip enabling the cache.
 */
@JvmInline public value class ApiKey private constructor(private val value: Pair<String, KeyInfo>) :
 java.io.Serializable,
 Comparable<ApiKey> {
  /** API key value held by this object; this value is considered sensitive and should not be logged. */
  public val key: String get() = value.first

  /** API key value held by this object; this value is considered sensitive and should not be logged. */
  public val type: SubjectType get() = value.second.type

  /** Return the format version of the underlying API key (always `1` at present). */
  public val version: Int get() = 1

  /** Static API key prefix constants. */
  internal object KeyPrefix {
    /** Key prefix for user API keys. */
    const val USER_SYMBOL: String = "user"

    /** Key prefix for org API keys. */
    const val ORG_SYMBOL: String = "org"
  }

  /**
   * ## API Key: Subject Type
   *
   * Enumerates the types of "subjects" (security principals) for which API keys are made available by the Buildless
   * service.
   */
  public enum class SubjectType(internal val symbol: String) {
    /**
     * ### API Key Type: User
     *
     * Specifies an API key which is minted for a Buildless user, and which authorizes an agent as a Buildless user.
     * User keys are not organization specific by nature.
     */
    USER(KeyPrefix.USER_SYMBOL),

    /**
     * ### API Key Type: Organization
     *
     * Specifies an API key which is minted for a Buildless organization tenant, and which authorizes an agent for data
     * access within that organization. Org keys are, by nature, not user specific.
     */
    ORG(KeyPrefix.ORG_SYMBOL);

    internal companion object {
      /** Detect the subject type to use for the specified key [value]. */
      @JvmStatic internal fun detect(value: String): SubjectType = when {
        value.startsWith("org_") -> ORG
        value.startsWith("user_") -> USER
        else -> error("API key has unrecognized prefix: '${value.substringBefore("_")}'")
      }
    }
  }

  /**
   * ## API Key: Key Info
   *
   * Describes information either detected or explicitly provided about an API key.
   *
   * @param type Subject type for the key.
   */
  internal data class KeyInfo(
    val type: SubjectType,
  )

  /**
   * ## Configuration Error: Invalid API Key
   *
   * Exception which is thrown when an API key (detected or provided) is found to be invalid. This is a configuration
   * error which must be remedied by the user.
   */
  public class InvalidKey(cause: Throwable) : InvalidConfiguration("Buildless API key is invalid", cause) {
    override val code: String get() = "API_KEY_INVALID"
  }

  /** Provides factory methods for easily creating [ApiKey] objects. */
  public companion object {
    /** Minimum key length. */
    private const val MIN_KEY_LENGTH: Int = 24

    /** Maximum key length. */
    private const val MAX_KEY_LENGTH: Int = 64

    // Set of allowed API key prefixes.
    private val allowedKeyPrefixes: SortedSet<String> = sortedSetOf("org", "user")

    // Routine to apply validation to the resolved `value`.
    private fun checkAndCreate(value: String, type: SubjectType?): ApiKey = try {
      require(value.isNotEmpty()) { "Buildless API key cannot be empty" }
      require(value.isNotBlank()) { "Buildless API key cannot be blank" }
      require(value.length >= MIN_KEY_LENGTH) { "Buildless API key is too short" }
      require(value.length <= MAX_KEY_LENGTH) { "Buildless API key is too long" }
      require(value.contains("_")) { "Buildless API key is malformed" }
      val prefix = value.substringBefore("_")
      require(prefix in allowedKeyPrefixes) { "Unrecognized key prefix: $prefix" }
      if (type != null) {
        require(prefix == type.symbol) { "Key prefix does not match provided type (expected '${type.symbol}')" }
      }
      ApiKey(value to KeyInfo(
        type = type ?: SubjectType.detect(value)
      ))
    } catch (err: IllegalArgumentException) {
      throw InvalidKey(err)
    }

    /**
     * Create an API key from the provided [value], and optionally a known [type].
     *
     * @param value API key value to use.
     * @param type Subject type for the key, if known. If unspecified, defaults to [SubjectType.USER].
     * @return API key object.
     * @throws InvalidKey if the key fails to pass static checks.
     */
    @Throws(InvalidKey::class)
    @JvmStatic
    public fun of(value: String, type: SubjectType? = null): ApiKey = checkAndCreate(
      value,
      type,
    )

    /**
     * Create a checked user API key from the provided [value].
     *
     * @param value API key value to use.
     * @return API key object.
     * @throws InvalidKey if the key fails to pass static checks.
     */
    @Throws(InvalidKey::class)
    @JvmStatic
    public fun forUser(value: String): ApiKey = of(value, SubjectType.USER)

    /**
     * Create a checked org tenant API key from the provided [value].
     *
     * @param value API key value to use.
     * @return API key object.
     * @throws InvalidKey if the key fails to pass static checks.
     */
    @Throws(InvalidKey::class)
    @JvmStatic
    public fun forOrg(value: String): ApiKey = of(value, SubjectType.ORG)
  }

  /**
   * Compare this API key to the [other] API key.
   *
   * @param other Other API key to compare with.
   * @return Comparison result.
   */
  override fun compareTo(other: ApiKey): Int {
    return key.compareTo(other.key)
  }

  /**
   * Generate a reasonable string representation which does not show the API key value.
   *
   * @return String representation.
   */
  override fun toString(): String = "ApiKey(present, type = ${value.second.type.name})"
}

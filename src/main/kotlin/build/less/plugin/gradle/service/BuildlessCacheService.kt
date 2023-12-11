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

package build.less.plugin.gradle.service

import build.less.plugin.gradle.BuildlessNativeTools
import build.less.plugin.gradle.BuildlessNativeToolsFactory
import org.gradle.caching.BuildCacheEntryReader
import org.gradle.caching.BuildCacheEntryWriter
import org.gradle.caching.BuildCacheKey
import org.gradle.caching.BuildCacheService
import org.gradle.caching.http.internal.HttpBuildCacheRequestCustomizer
import org.gradle.caching.http.internal.HttpBuildCacheService
import org.gradle.internal.resource.transport.http.Http2ClientHelper
import org.gradle.internal.resource.transport.http.HttpClientHelper
import java.net.URI

/**
 * # Buildless: Cache Service
 *
 * Build cache service implementing optimized transport with Buildless; see [BuildlessCache] for more information.
 * This service is shared throughout the Gradle build process, and is responsible for implementing optimized cache
 * protocols with the Buildless Agent.
 *
 * &nbsp;
 *
 * If the Buildless agent is not running, or for any other reason Buildless protocols are not available, this service
 * falls back to default Gradle cache behavior.
 *
 * @param endpoint Configured cache endpoint.
 * @param httpClientHelper HTTP client helper.
 * @param http2ClientHelper HTTP/2 client helper.
 * @param requestCustomizer HTTP request customizer.
 * @param useExpectContinue Whether to use the HTTP/1.1 `Expect: 100-continue` header.
 * @see [BuildlessCache] for more information.
 */
public class BuildlessCacheService(
  endpoint: URI,
  httpClientHelper: HttpClientHelper,
  http2ClientHelper: Http2ClientHelper,
  requestCustomizer: HttpBuildCacheRequestCustomizer,
  useExpectContinue: Boolean,
) : BuildCacheService, HttpBuildCacheService(
  httpClientHelper,
  endpoint,
  requestCustomizer,
  useExpectContinue,
) {
  // Native OS tools.
  private val nativeTools: BuildlessNativeTools by lazy {
    BuildlessNativeToolsFactory.obtain()
  }

  /**
   * ## Cache: Load
   *
   * Load a cache entry from the Buildless-powered cache; if Buildless-specific protocols are not available or the
   * Buildless Agent is not running, this will fall back to the default Gradle cache behavior.
   *
   * @param key Cache key to retrieve a cache entry for.
   * @param reader Cache entry reader.
   * @return `true` if the cache entry was loaded, `false` otherwise.
   */
  override fun load(key: BuildCacheKey, reader: BuildCacheEntryReader): Boolean {
    return super.load(key, reader)
  }

  /**
   * ## Cache: Store
   *
   * Store an entry within the Buildless-powered cache; if Buildless-specific protocols are not available, or the
   * Buildless Agent is not running, this will fall back to the default Gradle cache behavior.
   *
   * @param key Cache key to store a cache entry for.
   * @param writer Cache entry writer.
   */
  override fun store(key: BuildCacheKey, writer: BuildCacheEntryWriter) {
    super.store(key, writer)
  }
}

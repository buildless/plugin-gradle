package build.less.plugin.gradle

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/** Tests for the [ApiKey] utility class. */
class ApiKeyTest {
  // Assert that the specified `apiKey` is well-formed.
  private fun assertGood(apiKey: ApiKey?, message: String, expectedType: ApiKey.SubjectType) {
    assertNotNull(apiKey, "should not get `null` for API key factory output")
    assertNotNull(apiKey!!.key, "should not get `null` for API key value")
    assertNotNull(apiKey.type, "should not get `null` for API key type")
    assertEquals(1, apiKey.version, "version should be expected value: `1`")
    assertEquals(expectedType, apiKey.type, message)
  }

  @Test fun `api key factories should work for well-formed values`() {
    assertGood(
      ApiKey.of("user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"),
      "should be able to parse seemingly well-formed user API key",
      expectedType = ApiKey.SubjectType.USER,
    )
    assertGood(
      ApiKey.of(
        "user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123",
        type = ApiKey.SubjectType.USER,
      ),
      "should be able to parse seemingly well-formed user API key",
      expectedType = ApiKey.SubjectType.USER,
    )
    assertGood(
      ApiKey.forUser("user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"),
      "should be able to parse seemingly well-formed user API key",
      expectedType = ApiKey.SubjectType.USER,
    )
    assertGood(
      ApiKey.of("org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"),
      "should be able to parse well-formed org API key",
      expectedType = ApiKey.SubjectType.ORG,
    )
    assertGood(
      ApiKey.of(
        "org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123",
        type = ApiKey.SubjectType.ORG,
      ),
      "should be able to parse well-formed org API key",
      expectedType = ApiKey.SubjectType.ORG,
    )
    assertGood(
      ApiKey.forOrg("org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"),
      "should be able to parse well-formed org API key",
      expectedType = ApiKey.SubjectType.ORG,
    )
  }

  @Test fun `api key factories should reject for prefix mismatches`() {
    assertThrows<ApiKey.InvalidKey>("explicit api key type mismatch should fail") {
      ApiKey.of("user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123", type = ApiKey.SubjectType.ORG)
    }
    assertThrows<ApiKey.InvalidKey>("explicit api key type mismatch should fail") {
      ApiKey.of("org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123", type = ApiKey.SubjectType.USER)
    }
    assertThrows<ApiKey.InvalidKey>("explicit api key type mismatch should fail") {
      ApiKey.forOrg("user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123")
    }
    assertThrows<ApiKey.InvalidKey>("explicit api key type mismatch should fail") {
      ApiKey.forUser("org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123")
    }
  }

  @Test fun `api key should not reveal key value via toString`() {
    val key = "user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"
    assertFalse(
      ApiKey.of(key).toString().contains(key),
      "string representation of API key record should not reveal key itself in `toString`"
    )
  }

  @Test fun `api key should be comparable`() {
    val key = "user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"
    assertEquals(
      ApiKey.of(key),
      ApiKey.of(key),
      "API key records should be comparable by value"
    )

    val key2 = "org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"
    assertNotEquals(
      ApiKey.of(key),
      ApiKey.of(key2),
      "API key records which are different should compare as different"
    )

    val key3 = "user_ZBC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"
    assertNotEquals(
      ApiKey.of(key),
      ApiKey.of(key3),
      "API key records which are of the same type, but different, should compare as different"
    )
  }

  @Test fun `api key should be orderable`() {
    val k = "user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"
    val key = ApiKey.of(k)
    assertEquals(
      0,
      ApiKey.of(k).compareTo(key),
      "identical API key records should `compareTo` as identical"
    )
  }

  @Test fun `expected key prefixes should not be empty`() {
    assertTrue(ApiKey.KeyPrefix.ORG_SYMBOL.isNotEmpty())
    assertTrue(ApiKey.KeyPrefix.USER_SYMBOL.isNotEmpty())
  }

  @Test fun `api key should reject obviously malformed values`() {
    assertThrows<ApiKey.InvalidKey>("empty string is not a valid api key") {
      ApiKey.of("")
    }
    assertThrows<ApiKey.InvalidKey>("blank string is not a valid api key") {
      ApiKey.of(" ")
    }
    assertThrows<ApiKey.InvalidKey>("longer blank string is not a valid api key") {
      ApiKey.of(" \n ")
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of(".............")
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of("orgABC123ABC123ABC123ABC123ABC123")
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of("userABC123ABC123ABC123ABC123ABC123")
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of("bunk_ABC123ABC123ABC123ABC123ABC123")
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of("user_")
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of("org_")
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of(
        "user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"
      )
    }
    assertThrows<ApiKey.InvalidKey> {
      ApiKey.of(
        "org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"
      )
    }
  }

  @Test fun `api key type detection should work properly`() {
    assertEquals(
      ApiKey.SubjectType.ORG,
      ApiKey.of("org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123").type,
      "should be able to detect org API key type"
    )
    assertEquals(
      ApiKey.SubjectType.USER,
      ApiKey.of("user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123").type,
      "should be able to detect user API key type"
    )
    assertEquals(
      ApiKey.SubjectType.ORG,
      ApiKey.SubjectType.detect("org_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"),
    )
    assertEquals(
      ApiKey.SubjectType.USER,
      ApiKey.SubjectType.detect("user_ABC123ABC123ABC123ABC123ABC123ABC123ABC123ABC123"),
    )
    assertThrows<IllegalStateException> {
      ApiKey.SubjectType.detect("bunk_abcdabcdabcdabcdabcd")
    }
  }
}

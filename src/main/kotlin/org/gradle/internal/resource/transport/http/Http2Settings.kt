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

package org.gradle.internal.resource.transport.http

import org.gradle.authentication.Authentication
import org.gradle.internal.resource.transport.http.HttpSettings.RedirectMethodHandlingStrategy
import org.gradle.internal.verifier.HttpRedirectVerifier
import java.util.*

/**
 * TBD.
 */
public interface Http2Settings : HttpSettings {
  // Nothing at this time.

  public companion object {
    @JvmStatic public fun wrapping(
      sslContextFactory: SslContextFactory,
      redirectVerifier: HttpRedirectVerifier,
      redirectStrategy: Optional<RedirectMethodHandlingStrategy> = Optional.empty(),
      authorizors: List<Authentication> = emptyList(),
    ): Http2Settings {
      val base = DefaultHttpSettings.builder()
        .withAuthenticationSettings(authorizors)
        .withRedirectVerifier(redirectVerifier)
        .withSslContextFactory(sslContextFactory).apply {
          // attach strategy if present
          if (redirectStrategy.isPresent) {
            withRedirectMethodHandlingStrategy(redirectStrategy.get())
          }
      }.build()

      return object: Http2Settings, HttpSettings by base {
        // Nothing at this time.
      }
    }
  }
}

@file:Suppress("detekt:all", "unused", "unused_parameter")

package org.gradle.internal.resource.transport.http

import org.gradle.internal.impldep.org.apache.http.client.methods.CloseableHttpResponse
import java.net.URI

/**
 * TBD.
 */
public object HttpClientResponseFactory {
  @JvmStatic public fun create(method: String, effective: URI, response: CloseableHttpResponse): HttpClientResponse {
    TODO("not yet implemented")
  }
}

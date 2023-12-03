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

@file:Suppress("detekt:all")

package org.gradle.internal.resource.transport.http


import okhttp3.OkHttpClient
import org.gradle.api.internal.DocumentationRegistry
import org.gradle.internal.UncheckedException
import org.gradle.internal.impldep.com.google.common.annotations.VisibleForTesting
import org.gradle.internal.impldep.com.google.common.collect.Iterables
import org.gradle.internal.impldep.org.apache.http.client.methods.CloseableHttpResponse
import org.gradle.internal.impldep.org.apache.http.client.methods.HttpGet
import org.gradle.internal.impldep.org.apache.http.client.methods.HttpHead
import org.gradle.internal.impldep.org.apache.http.client.methods.HttpRequestBase
import org.gradle.internal.impldep.org.apache.http.client.utils.URIBuilder
import org.gradle.internal.impldep.org.apache.http.protocol.BasicHttpContext
import org.gradle.internal.impldep.org.apache.http.protocol.HttpContext
import org.gradle.internal.resource.transport.http.*
import org.gradle.internal.resource.transport.http.Http2ClientHelper.Factory
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.io.IOException
import java.net.SocketException
import java.net.URI
import java.net.URISyntaxException
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import javax.annotation.Nonnull
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

/**
 * TBD.
 */
public class Http2ClientHelper internal constructor (
  private val docRegistry: DocumentationRegistry,
  private val settings: Http2Settings,
) : Closeable {
  private companion object {
    private val LOGGER = LoggerFactory.getLogger(Http2ClientHelper::class.java)
  }

  // Backing HTTP/1.1 helper.
  private val helper: HttpClientHelper = HttpClientHelper.Factory.createFactory(docRegistry).create(settings)
  private val initialized: AtomicBoolean = AtomicBoolean(false)
  private val active: AtomicBoolean = AtomicBoolean(false)
  private val client: AtomicReference<OkHttpClient> = AtomicReference(null)
  private val sharedContext: ConcurrentLinkedQueue<HttpContext> = ConcurrentLinkedQueue()
  private val supportedTlsVersions: Collection<String> = listOf("")

  private fun performRawHead(source: String, revalidate: Boolean): HttpClientResponse {
    return performRequest(HttpHead(source), revalidate)
  }

  private fun performRawGet(source: String?, revalidate: Boolean): HttpClientResponse {
    return performRequest(HttpGet(source), revalidate)
  }

  private fun performRequest(request: HttpRequestBase, revalidate: Boolean): HttpClientResponse {
    val method = request.method
    if (revalidate) {
      request.addHeader("Cache-Control", "max-age=0")
    }
    return try {
      executeGetOrHead(request)
    } catch (var5: FailureFromRedirectLocation) {
      throw createHttpRequestException(method, var5.cause, var5.lastRedirect)
    } catch (var6: IOException) {
      throw createHttpRequestException(method, wrapWithExplanation(var6), request.uri)
    }
  }

  @Nonnull
  private fun createHttpRequestException(method: String, cause: Throwable?, uri: URI): HttpRequestException {
    return HttpRequestException(String.format("Could not %s '%s'.", method, stripUserCredentials(uri)), cause)
  }

  private fun wrapWithExplanation(e: IOException): Exception {
    return if (e !is SocketException && (e !is SSLException || !e.message!!.contains("readHandshakeRecord"))) {
      if (e !is SSLHandshakeException) {
        e
      } else {
        val condition = (
          e.message?.contains("PKIX path building failed") != true &&
          e.message?.contains("certificate_unknown") != true
        )
        val message = if (condition) {
            String.format(
              "The server %s not support the client's requested TLS protocol versions: (%s). You may need to " +
              "configure the client to allow other protocols to be used. %s",
              getConfidenceNote(
                e
              ),
              java.lang.String.join(", ", supportedTlsVersions),
              docRegistry.getDocumentationRecommendationFor(
                "on this",
                "build_environment",
                "sec:gradle_system_properties"
              )
            )
          } else {
            "Got SSL handshake exception during request. It might be caused by SSL misconfiguration"
          }
        HttpRequestException(message, e)
      }
    } else {
      HttpRequestException("Got socket exception during request. It might be caused by SSL misconfiguration", e)
    }
  }

  @Nonnull
  private fun getConfidenceNote(sslException: SSLHandshakeException): String {
    return if (sslException.message != null && sslException.message!!.contains("protocol_version")) {
      "does"
    } else {
      "may"
    }
  }

  @Throws(IOException::class)
  protected fun executeGetOrHead(method: HttpRequestBase): HttpClientResponse {
    val response = this.performHttpRequest(method)
    if (!response.wasSuccessful()) {
      response.close()
    }
    return response
  }

  public fun performHead(source: String, revalidate: Boolean): HttpClientResponse {
    return processResponse(performRawHead(source, revalidate))
  }

  @Nonnull
  public fun performGet(source: String?, revalidate: Boolean): HttpClientResponse {
    return processResponse(performRawGet(source, revalidate))
  }

  @Throws(IOException::class)
  public fun performHttpRequest(request: HttpRequestBase): HttpClientResponse {
    return if (this.sharedContext == null) {
      this.performHttpRequest(request, BasicHttpContext())
    } else {
      val httpContext = nextAvailableSharedContext()
      val var3: HttpClientResponse
      var3 = try {
        this.performHttpRequest(request, httpContext)
      } finally {
        this.sharedContext.add(httpContext)
      }
      var3
    }
  }

  private fun nextAvailableSharedContext(): HttpContext {
    return (sharedContext.poll() ?: BasicHttpContext())
  }

  @Throws(IOException::class)
  private fun performHttpRequest(request: HttpRequestBase, httpContext: HttpContext): HttpClientResponse {
    httpContext.removeAttribute("http.protocol.redirect-locations")
    LOGGER.debug("Performing HTTP {}: {}", request.method, stripUserCredentials(request.uri))
    return try {
//      val response = getClient().execute(request, httpContext)
//      toHttpClientResponse(request, httpContext, response)
      TODO("not yet implemented")
    } catch (var5: IOException) {
      validateRedirectChain(httpContext)
      val lastRedirectLocation = stripUserCredentials(getLastRedirectLocation(httpContext))
      throw lastRedirectLocation?.let { FailureFromRedirectLocation(it, var5) } ?: var5
    }
  }

  private fun toHttpClientResponse(
    request: HttpRequestBase,
    httpContext: HttpContext,
    response: CloseableHttpResponse
  ): HttpClientResponse {
    validateRedirectChain(httpContext)
    val lastRedirectLocation = getLastRedirectLocation(httpContext)
    val effectiveUri = lastRedirectLocation ?: request.uri
    return HttpClientResponseFactory.create(request.method, effectiveUri, response)
  }

  private fun validateRedirectChain(httpContext: HttpContext) {
    settings.redirectVerifier.validateRedirects(getRedirectLocations(httpContext))
  }

  @Nonnull
  private fun getRedirectLocations(httpContext: HttpContext): List<URI> {
    val redirects: List<URI> = httpContext.getAttribute("http.protocol.redirect-locations") as List<URI>
    return redirects ?: emptyList()
  }

  private fun getLastRedirectLocation(httpContext: HttpContext): URI? {
    val redirectLocations = getRedirectLocations(httpContext)
    return if (redirectLocations.isEmpty()) null else Iterables.getLast(redirectLocations) as URI
  }

  @Nonnull
  private fun processResponse(response: HttpClientResponse): HttpClientResponse {
    return if (response.wasMissing()) {
      LOGGER.info(
        "Resource missing. [HTTP {}: {}]",
        response.method,
        stripUserCredentials(response.effectiveUri)
      )
      response
    } else if (response.wasSuccessful()) {
      response
    } else {
      val effectiveUri = stripUserCredentials(response.effectiveUri)
      LOGGER.info(
        "Failed to get resource: {}. [HTTP {}: {})]",
        *arrayOf(response.method, response.statusLine, effectiveUri)
      )
      throw HttpErrorStatusCodeException(
        response.method,
        effectiveUri.toString(),
        response.statusLine.statusCode,
        response.statusLine.reasonPhrase
      )
    }
  }

  @Synchronized private fun getClient(): OkHttpClient {
    if (!this.initialized.get()) {
      val builder = OkHttpClient.Builder()
      val configurer = DefaultHttp2ClientConfigurer(settings)
      configurer.configure(builder)
      client.set(builder.build())
      initialized.compareAndSet(false, true)
      active.compareAndSet(false, true)
    }
    return client.get()
  }

  @Synchronized
  @Throws(IOException::class)
  override fun close() {
    if (initialized.get() && active.get()) {
      active.compareAndSet(true, false)
      client.get().dispatcher.executorService.shutdown()
      sharedContext.clear()
    }
  }

  @VisibleForTesting
  internal fun stripUserCredentials(uri: URI?): URI? {
    return if (uri == null) {
      null
    } else {
      try {
        URIBuilder(uri).setUserInfo(null as String?).build()
      } catch (var2: URISyntaxException) {
        throw UncheckedException.throwAsUncheckedException(var2, true)
      }
    }
  }

  @FunctionalInterface
  public fun interface Factory {
    public fun create(settings: Http2Settings): Http2ClientHelper

    public companion object {
      public fun createFactory(documentationRegistry: DocumentationRegistry): Factory {
        return Factory { settings: Http2Settings ->
          Http2ClientHelper(documentationRegistry, settings)
        }
      }
    }
  }

  private class FailureFromRedirectLocation(val lastRedirect: URI, cause: Throwable) : IOException(cause)
}

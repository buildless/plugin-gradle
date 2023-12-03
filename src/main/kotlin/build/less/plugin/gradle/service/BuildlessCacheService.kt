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

/** Build cache service implementing optimized transport with Buildless; see [BuildlessCache]. */
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

  override fun load(key: BuildCacheKey, reader: BuildCacheEntryReader): Boolean {
    return super.load(key, reader)
  }

  override fun store(key: BuildCacheKey, writer: BuildCacheEntryWriter) {
    super.store(key, writer)
  }
}

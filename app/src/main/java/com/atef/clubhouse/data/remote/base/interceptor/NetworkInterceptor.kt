package com.atef.clubhouse.data.remote.base.interceptor

import java.util.concurrent.TimeUnit
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * This interceptor will be called ONLY if the network is available
 * Caching when network is on
 */

class NetworkInterceptor : Interceptor {

    companion object {
        private const val HEADER_PRAGMA = "Pragma"
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        // Authorization
        val newRequestBuilder = request.newBuilder()
        val response: Response = chain.proceed(newRequestBuilder.build())
        // Http Cache
        return enableHttpCache(response)
    }

    private fun enableHttpCache(response: Response): Response {
        val header: String? = response.header(HEADER_CACHE_CONTROL)
        return if (header == null ||
            !(header.contains("no-store") ||
                header.contains("no-cache") ||
                header.contains("must-revalidate") ||
                header.contains("max-stale=0") ||
                header.contains("max-age=0"))
        ) {
            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.MINUTES).build()
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        } else response
    }
}

package com.atef.clubhouse.data.remote.base.interceptor

import android.content.Context
import com.atef.clubhouse.data.feature.auth.AuthLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.util.UUID
import java.util.Locale
import javax.inject.Inject

class HeadersInterceptor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authLocalDataSource: AuthLocalDataSource,
) : Interceptor {

    private val singleThreadCoroutineScope =
        CoroutineScope(newSingleThreadContext(HeadersInterceptor::class.java.simpleName))

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestBuilder = request.newBuilder()
        val locale = context.resources.configuration.locales
        val deviceId = UUID.randomUUID().toString().toUpperCase(Locale.getDefault())
        requestBuilder
            .addHeader("CH-Languages", locale.toLanguageTags())
            .addHeader("CH-Locale", locale.get(0).toLanguageTag().replace('-', '_'))
            .addHeader("Accept", "application/json")
            .addHeader("CH-AppBuild", "304")
            .addHeader("CH-AppVersion", "0.1.28")
            .addHeader("User-Agent", "clubhouse/304 (iPhone; iOS 13.5.1; Scale/3.00)")
            .addHeader("CH-DeviceId", deviceId)

        val userIdHeader = chain.request().header(USER_ID_HEADER)
        val tokenHeader = chain.request().header(HEADER_TOKEN)
        if (userIdHeader != null && userIdHeader.length <= 1 ||
            tokenHeader != null && tokenHeader.length <= 1
        ) {
            runBlocking {
                requestBuilder.removeHeader(USER_ID_HEADER)
                requestBuilder.removeHeader(HEADER_TOKEN)
                requestBuilder.addHeader(USER_ID_HEADER, authLocalDataSource.user.first()?.userId.toString())
                requestBuilder.addHeader(HEADER_TOKEN, "token "+authLocalDataSource.token.first().toString())
            }
        }
        request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        private const val USER_ID_HEADER = "CH-UserID"
        private const val HEADER_TOKEN = "Authorization"
    }

}
package com.atef.clubhouse.data.base

import android.content.Context
import com.atef.clubhouse.data.remote.base.calladapter.NetworkResponseAdapterFactory
import com.atef.clubhouse.data.remote.base.interceptor.ConnectivityInterceptor
import com.atef.clubhouse.data.remote.base.interceptor.NetworkInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.util.concurrent.TimeUnit
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun makeServiceHandler(
        baseUrl: String,
        classTarget: Class<*>,
        isDebug: Boolean,
        context: Context
    ): Any {
        val okHttpClient = makeOkHttpClient(
            makeHttpCache(context),
            makeNetworkInterceptor(),
            makeLoggingInterceptor(isDebug),
            makeConnectivityInterceptor(context)
        )
        return makeApiHandler(
            baseUrl,
            okHttpClient,
            makeGson(),
            classTarget
        )
    }

    private fun makeApiHandler(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson,
        classTarget: Class<*>
    ): Any {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(classTarget)
    }

    private fun makeOkHttpClient(
        cache: Cache,
        networkInterceptor: NetworkInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(connectivityInterceptor)
            .addNetworkInterceptor(networkInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    }

    private fun makeHttpCache(appContext: Context): Cache {
        val cacheDir = appContext.cacheDir
        val httpCacheSize: Long = 10 * 1024 * 1024 // 10 MB
        val httpCacheDirectory = File(cacheDir, "offlineCache")
        return Cache(httpCacheDirectory, httpCacheSize)
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun makeConnectivityInterceptor(context: Context) =
        ConnectivityInterceptor(context)

    private fun makeNetworkInterceptor() = NetworkInterceptor()
}

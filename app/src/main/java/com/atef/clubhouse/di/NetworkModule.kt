package com.atef.clubhouse.di

import android.content.Context
import com.atef.clubhouse.BuildConfig
import com.atef.clubhouse.data.base.RetrofitFactory
import com.atef.clubhouse.data.remote.feature.login.service.LoginApiHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @[Provides Singleton]
    fun provideBaseURL(): String = BuildConfig.BASE_URL

    @[Provides Singleton]
    fun provideApiHandler(
        baseUrl: String,
        @ApplicationContext context: Context
    ): LoginApiHandler {
        return RetrofitFactory.makeServiceHandler(
            baseUrl,
            LoginApiHandler::class.java,
            BuildConfig.DEBUG,
            context
        ) as LoginApiHandler
    }
}

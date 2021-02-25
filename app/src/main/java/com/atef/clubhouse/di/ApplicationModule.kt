package com.atef.clubhouse.di

import android.content.Context
import android.content.SharedPreferences
import com.atef.clubhouse.data.local.PreferencesGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(APP_PACKAGE, Context.MODE_PRIVATE)
    }

    @[Provides Singleton]
    fun providePreferencesGateway(prefs: SharedPreferences): PreferencesGateway {
        return PreferencesGateway(prefs)
    }

    companion object {
        const val APP_PACKAGE = "com.atef.clubhouse"
    }
}

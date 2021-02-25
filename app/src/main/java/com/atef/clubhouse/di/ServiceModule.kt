package com.atef.clubhouse.di

import com.atef.clubhouse.cloudmessaging.StatusManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class ServiceModule {

    @[Provides Singleton]
    fun provideStatusManager() = StatusManager()
}

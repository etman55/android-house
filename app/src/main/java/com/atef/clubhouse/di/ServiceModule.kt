package com.atef.clubhouse.di

import com.atef.clubhouse.cloudmessaging.StatusManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
@InstallIn(ApplicationComponent::class)
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideStatusManager() = StatusManager()
}

package com.atef.clubhouse.di

import com.atef.clubhouse.utils.ImageLoader
import com.atef.clubhouse.utils.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface ImageLoaderModule {

    @get:[Binds Singleton]
    val ImageLoaderImpl.imageLoader: ImageLoader
}

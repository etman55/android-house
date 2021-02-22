package com.atef.clubhouse.di

import com.atef.clubhouse.utils.ImageLoader
import com.atef.clubhouse.utils.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface ImageLoaderModule {

    @get:Binds
    val ImageLoaderImpl.imageLoader: ImageLoader
}

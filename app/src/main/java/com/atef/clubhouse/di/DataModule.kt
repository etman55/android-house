package com.atef.clubhouse.di

import com.atef.clubhouse.data.feature.auth.AuthLocalDataSource
import com.atef.clubhouse.data.feature.auth.WarningLocalDataSource
import com.atef.clubhouse.data.local.feature.auth.AuthLocalDataSourceImpl
import com.atef.clubhouse.data.local.feature.country.CountryRepositoryImpl
import com.atef.clubhouse.data.local.feature.warning.WarningLocalDataSourceImpl
import com.atef.clubhouse.data.repository.AuthRepositoryImpl
import com.atef.clubhouse.data.repository.ChannelsRepositoryImpl
import com.atef.clubhouse.domain.repository.AuthRepository
import com.atef.clubhouse.domain.repository.CountryRepository
import com.atef.clubhouse.domain.repository.ChannelsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@InstallIn(ApplicationComponent::class)
@Module
interface DataModule {

    @get:[Binds Singleton]
    val CountryRepositoryImpl.countryRepository: CountryRepository

    @get:[Binds Singleton]
    val AuthRepositoryImpl.authRepository: AuthRepository

    @get:[Binds Singleton]
    val ChannelsRepositoryImpl.channelsRepository: ChannelsRepository

    @get:[Binds Singleton]
    val AuthLocalDataSourceImpl.authLocalDataSource: AuthLocalDataSource

    @get:[Binds Singleton]
    val WarningLocalDataSourceImpl.warningLocalDataSource: WarningLocalDataSource

}

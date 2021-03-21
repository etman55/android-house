package com.atef.clubhouse.di

import com.atef.clubhouse.data.base.mapper.RemoteModelMapper
import com.atef.clubhouse.data.feature.auth.AuthRemoteDataSource
import com.atef.clubhouse.data.feature.home.ChannelsRemoteDataSource
import com.atef.clubhouse.data.remote.feature.auth.AuthRemoteDataSourceImpl
import com.atef.clubhouse.data.remote.feature.auth.mapper.ProfileMapper
import com.atef.clubhouse.data.remote.feature.auth.mapper.UserMapper
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.data.remote.feature.auth.model.ProfileResponse
import com.atef.clubhouse.data.remote.feature.home.ChannelsRemoteDataSourceImpl
import com.atef.clubhouse.data.remote.feature.home.mapper.ChannelMapper
import com.atef.clubhouse.data.remote.feature.home.model.ChannelResponse
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.entity.home.Channel
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
interface RemoteModule {

    @get:[Binds Singleton]
    val AuthRemoteDataSourceImpl.authRemoteDataSource: AuthRemoteDataSource

    @get:[Binds Singleton]
    val ChannelsRemoteDataSourceImpl.channelsRemoteDataSource: ChannelsRemoteDataSource

    @get:[Binds Singleton]
    val UserMapper.userMapper: RemoteModelMapper<CompletePhoneNumberAuthResponse, User>

    @get:[Binds Singleton]
    val ProfileMapper.profileMapper: RemoteModelMapper<ProfileResponse, User>

    @get:[Binds Singleton]
    val ChannelMapper.channelMapper: RemoteModelMapper<ChannelResponse, Channel>
}
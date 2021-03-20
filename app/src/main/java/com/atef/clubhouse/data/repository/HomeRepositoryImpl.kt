package com.atef.clubhouse.data.repository

import com.atef.clubhouse.data.feature.auth.AuthLocalDataSource
import com.atef.clubhouse.data.feature.auth.AuthRemoteDataSource
import com.atef.clubhouse.data.feature.home.HomeRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.auth.mapper.UserMapper
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.data.remote.feature.home.mapper.ChannelMapper
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.entity.home.Channel
import com.atef.clubhouse.domain.repository.AuthRepository
import com.atef.clubhouse.domain.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource,
    private val mapper: ChannelMapper,
) : HomeRepository {

    override suspend fun getChannels(): Result<ChannelsResponse> {
        return remote.getChannels()
    }
}
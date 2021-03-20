package com.atef.clubhouse.data.feature.home

import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse
import com.atef.clubhouse.domain.entity.auth.User
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {
    suspend fun getChannels(): Result<ChannelsResponse>

}
package com.atef.clubhouse.data.remote.feature.home

import com.atef.clubhouse.data.feature.home.ChannelsRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse
import com.atef.clubhouse.data.remote.feature.home.service.ChannelsApiHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class ChannelsRemoteDataSourceImpl @Inject constructor(
        private val api: ChannelsApiHandler
) : ChannelsRemoteDataSource {

    override suspend fun getChannels(): Result<ChannelsResponse> = api.getChannels()

}
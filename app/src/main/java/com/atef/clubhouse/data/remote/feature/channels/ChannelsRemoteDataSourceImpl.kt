package com.atef.clubhouse.data.remote.feature.channels

import com.atef.clubhouse.data.feature.channels.ChannelsRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.channels.model.ChannelsResponse
import com.atef.clubhouse.data.remote.feature.channels.service.ChannelsApiHandler
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
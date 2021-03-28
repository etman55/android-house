package com.atef.clubhouse.data.feature.channels

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.channels.model.ChannelsResponse

interface ChannelsRemoteDataSource {
    suspend fun getChannels(): Result<ChannelsResponse>

}
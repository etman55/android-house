package com.atef.clubhouse.data.remote.feature.channels.service

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.channels.model.ChannelsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ChannelsApiHandler {
    @Headers("CH-UserID:?", "Authorization:?")
    @GET("get_channels")
    suspend fun getChannels(): Result<ChannelsResponse>
}

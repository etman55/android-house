package com.atef.clubhouse.data.remote.feature.home.service

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeApiHandler {
    @Headers("CH-UserID:?", "Authorization:?")
    @GET("get_channels")
    suspend fun getChannels(): Result<ChannelsResponse>
}

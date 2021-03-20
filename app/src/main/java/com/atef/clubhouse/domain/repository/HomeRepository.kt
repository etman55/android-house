package com.atef.clubhouse.domain.repository

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse

interface HomeRepository {

    suspend fun getChannels(
    ): Result<ChannelsResponse>

}
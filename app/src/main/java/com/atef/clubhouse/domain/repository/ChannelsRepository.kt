package com.atef.clubhouse.domain.repository

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.domain.entity.channels.Channel

interface ChannelsRepository {

    suspend fun getChannels(): Result<List<Channel>>

}
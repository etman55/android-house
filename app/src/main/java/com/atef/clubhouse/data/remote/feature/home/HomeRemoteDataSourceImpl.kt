package com.atef.clubhouse.data.remote.feature.home

import com.atef.clubhouse.data.feature.home.HomeRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.home.mapper.ChannelMapper
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse
import com.atef.clubhouse.data.remote.feature.home.service.HomeApiHandler
import com.atef.clubhouse.domain.entity.home.Channel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeRemoteDataSourceImpl @Inject constructor(
        private val api: HomeApiHandler,
        private val mapper: ChannelMapper,
) : HomeRemoteDataSource {

    private val channelChannel = ConflatedBroadcastChannel<Channel?>()

    override suspend fun getChannels(): Result<ChannelsResponse> {
        return api.getChannels()
                .map { result ->
                    result.channels?.map { model -> mapper.mapFromModel(model) }
                    result
                }
    }

}
package com.atef.clubhouse.data.repository

import com.atef.clubhouse.data.feature.home.ChannelsRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.channels.mapper.ChannelMapper
import com.atef.clubhouse.domain.entity.channels.Channel
import com.atef.clubhouse.domain.repository.ChannelsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class ChannelsRepositoryImpl @Inject constructor(
        private val remote: ChannelsRemoteDataSource,
        private val mapper: ChannelMapper,
) : ChannelsRepository {

    override suspend fun getChannels(): Result<List<Channel>> {
        return remote.getChannels().map {
            it.channels?.let { it1 ->mapper.mapModelList(it1) }!!
        }
    }
}
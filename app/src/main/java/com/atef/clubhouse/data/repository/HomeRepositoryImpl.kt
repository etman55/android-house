package com.atef.clubhouse.data.repository

import com.atef.clubhouse.data.feature.home.HomeRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.home.mapper.ChannelMapper
import com.atef.clubhouse.domain.entity.home.Channel
import com.atef.clubhouse.domain.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource,
    private val mapper: ChannelMapper,
) : HomeRepository {

    override suspend fun getChannels(): Result<List<Channel>> {
        return remote.getChannels().map {
            it.channels?.let { it1 ->mapper.mapModelList(it1) }!!
        }
    }
}
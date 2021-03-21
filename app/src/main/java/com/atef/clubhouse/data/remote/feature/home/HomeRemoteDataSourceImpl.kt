package com.atef.clubhouse.data.remote.feature.home

import com.atef.clubhouse.data.feature.home.HomeRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse
import com.atef.clubhouse.data.remote.feature.home.service.HomeApiHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeRemoteDataSourceImpl @Inject constructor(
        private val api: HomeApiHandler
) : HomeRemoteDataSource {

    override suspend fun getChannels(): Result<ChannelsResponse> = api.getChannels()

}
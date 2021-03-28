package com.atef.clubhouse.domain.feature.home

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.entity.channels.Channel
import com.atef.clubhouse.domain.repository.ChannelsRepository
import javax.inject.Inject

class GetChannelsUseCase @Inject constructor(
        private val channelsRepository: ChannelsRepository,
        dispatcherProvider: CoroutineDispatcherProvider,
) : SuspendingInteractor<Nothing?, Result<List<Channel>>>(dispatcherProvider) {

    override suspend fun execute(params: Nothing?): Result<List<Channel>> {
        return channelsRepository.getChannels()
    }

}
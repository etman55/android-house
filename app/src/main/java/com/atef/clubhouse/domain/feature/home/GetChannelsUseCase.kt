package com.atef.clubhouse.domain.feature.home

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.home.model.ChannelsResponse
import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.repository.HomeRepository
import javax.inject.Inject

class GetChannelsUseCase @Inject constructor(
        private val homeRepository: HomeRepository,
        dispatcherProvider: CoroutineDispatcherProvider,
) : SuspendingInteractor<Nothing?, Result<ChannelsResponse>>(dispatcherProvider) {

    override suspend fun execute(params: Nothing?): Result<ChannelsResponse> {
        return homeRepository.getChannels()
    }

}
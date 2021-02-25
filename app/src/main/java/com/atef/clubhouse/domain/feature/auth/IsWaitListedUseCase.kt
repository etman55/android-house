package com.atef.clubhouse.domain.feature.auth

import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.FlowInteractor
import com.atef.clubhouse.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsWaitListedUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatcherProvider: CoroutineDispatcherProvider,
) : FlowInteractor<Nothing?, Boolean?>(dispatcherProvider) {
    override fun execute(params: Nothing?): Flow<Boolean?> {
        return authRepository.isWaitListed
    }
}
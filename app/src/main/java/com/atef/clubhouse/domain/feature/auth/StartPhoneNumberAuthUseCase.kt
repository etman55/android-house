package com.atef.clubhouse.domain.feature.auth

import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.repository.AuthRepository
import javax.inject.Inject

class StartPhoneNumberAuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatcherProvider: CoroutineDispatcherProvider,
) : SuspendingInteractor<StartPhoneNumberAuthUseCase.Params, Result<BaseResponse>>(dispatcherProvider) {

    override suspend fun execute(params: Params?): Result<BaseResponse> {
        requireNotNull(params)
        return authRepository.startPhoneNumberAuth(params.phoneNumber)
    }

    data class Params(val phoneNumber: String)
}
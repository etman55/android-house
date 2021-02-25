package com.atef.clubhouse.domain.feature.auth

import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.repository.AuthRepository
import javax.inject.Inject

class ResendVerificationCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatcherProvider: CoroutineDispatcherProvider
) : SuspendingInteractor<ResendVerificationCodeUseCase.Params, Result<BaseResponse>>(dispatcherProvider) {

    override suspend fun execute(params: Params?): Result<BaseResponse> {
        requireNotNull(params)
        return authRepository.resendVerificationCode(params.phoneNumber)
    }

    data class Params(val phoneNumber: String)

}
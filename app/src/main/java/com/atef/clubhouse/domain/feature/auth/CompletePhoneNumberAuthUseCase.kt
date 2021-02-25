package com.atef.clubhouse.domain.feature.auth

import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.repository.AuthRepository
import javax.inject.Inject

class CompletePhoneNumberAuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatcherProvider: CoroutineDispatcherProvider,
) : SuspendingInteractor<CompletePhoneNumberAuthUseCase.Params, Result<CompletePhoneNumberAuthResponse>>(dispatcherProvider) {

    override suspend fun execute(params: Params?):Result<CompletePhoneNumberAuthResponse> {
        requireNotNull(params)
        return authRepository.completePhoneNumberAuth(params.phoneNumber, params.verificationCode)
    }

    data class Params(val phoneNumber: String, val verificationCode: String)
}
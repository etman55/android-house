package com.atef.clubhouse.domain.feature.auth

import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.FlowInteractor
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatcherProvider: CoroutineDispatcherProvider,
) : FlowInteractor<Nothing?, User?>(dispatcherProvider) {
    override fun execute(params: Nothing?): Flow<User?> {
        return authRepository.user
    }
}
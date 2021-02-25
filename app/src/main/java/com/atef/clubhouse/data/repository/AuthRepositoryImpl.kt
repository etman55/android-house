package com.atef.clubhouse.data.repository

import com.atef.clubhouse.data.feature.auth.AuthLocalDataSource
import com.atef.clubhouse.data.feature.auth.AuthRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.auth.mapper.UserMapper
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource,
    private val mapper: UserMapper,
) : AuthRepository {
    override val user: Flow<User?> = local.user
    override val token: Flow<String> = local.token
    override val isWaitListed: Flow<Boolean> = local.isWaitListed

    override suspend fun startPhoneNumberAuth(phoneNumber: String): Result<BaseResponse> {
        return remote.startPhoneNumberAuth(phoneNumber)
    }

    override suspend fun resendVerificationCode(phoneNumber: String): Result<BaseResponse> {
        return remote.resendVerificationCode(phoneNumber)
    }

    override suspend fun completePhoneNumberAuth(
        phoneNumber: String,
        verificationCode: String,
    ): Result<CompletePhoneNumberAuthResponse> {
        val result = remote.completePhoneNumberAuth(phoneNumber, verificationCode)
        if (result is Result.Success) {
            // cache user auth token
            result.data.authToken?.let { local.saveToken(it) }
            // cache user
            local.saveUser(mapper.mapFromModel(result.data))
        }
        return result
    }

    override suspend fun updateUsername(username: String): Result<BaseResponse> {
        return remote.updateUsername(username)
    }

    override suspend fun updateName(name: String): Result<BaseResponse> {
        return remote.updateName(name)
    }
}
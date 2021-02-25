package com.atef.clubhouse.domain.repository

import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.domain.entity.auth.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val user: Flow<User?>
    val isWaitListed: Flow<Boolean?>
    val token: Flow<String>

    suspend fun startPhoneNumberAuth(phoneNumber: String): Result<BaseResponse>

    suspend fun resendVerificationCode(phoneNumber: String): Result<BaseResponse>

    suspend fun completePhoneNumberAuth(
        phoneNumber: String,
        verificationCode: String,
    ): Result<CompletePhoneNumberAuthResponse>

    suspend fun updateUsername(username: String): Result<BaseResponse>

    suspend fun updateName(name: String): Result<BaseResponse>

}
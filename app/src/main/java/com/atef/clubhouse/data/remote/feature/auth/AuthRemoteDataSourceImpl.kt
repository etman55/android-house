package com.atef.clubhouse.data.remote.feature.auth

import com.atef.clubhouse.data.feature.auth.AuthRemoteDataSource
import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.auth.mapper.UserMapper
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.data.remote.feature.auth.model.UpdateNameBody
import com.atef.clubhouse.data.remote.feature.auth.model.UpdateUsernameBody
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthBody
import com.atef.clubhouse.data.remote.feature.auth.model.ResendVerificationCodeBody
import com.atef.clubhouse.data.remote.feature.auth.model.StartPhoneNumberAuthBody
import com.atef.clubhouse.data.remote.feature.auth.service.AuthApiHandler
import com.atef.clubhouse.domain.entity.auth.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class AuthRemoteDataSourceImpl @Inject constructor(
    private val api: AuthApiHandler,
    private val mapper: UserMapper,
) : AuthRemoteDataSource {

    private val userChannel = ConflatedBroadcastChannel<User?>()
    override val user = userChannel.asFlow()

    private val isWaitingListChannel = ConflatedBroadcastChannel<Boolean>()
    override val isWaitingListed = isWaitingListChannel.asFlow()

    override suspend fun startPhoneNumberAuth(phoneNumber: String): Result<BaseResponse> {
        return api.startPhoneNumberAuth(StartPhoneNumberAuthBody(phoneNumber))
    }

    override suspend fun resendVerificationCode(phoneNumber: String): Result<BaseResponse> {
        return api.resendVerificationCode(ResendVerificationCodeBody(phoneNumber))
    }

    override suspend fun completePhoneNumberAuth(
        phoneNumber: String,
        verificationCode: String,
    ): Result<CompletePhoneNumberAuthResponse> {
        return api.completePhoneNumberAuth(CompletePhoneNumberAuthBody(phoneNumber, verificationCode))
            .map { result ->
                userChannel.offer(mapper.mapFromModel(result))
                result.isWaitListed?.let { isWaitingListChannel.offer(it) }
                result
            }
    }

    override suspend fun updateUsername(username: String): Result<BaseResponse> {
        return api.updateUsername(UpdateUsernameBody(username))
    }

    override suspend fun updateName(name: String): Result<BaseResponse> {
        return api.updateName(UpdateNameBody(name))
    }
}
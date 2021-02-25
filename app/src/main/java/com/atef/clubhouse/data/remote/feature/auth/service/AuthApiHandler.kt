package com.atef.clubhouse.data.remote.feature.auth.service

import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.data.remote.feature.auth.model.StartPhoneNumberAuthBody
import com.atef.clubhouse.data.remote.feature.auth.model.ResendVerificationCodeBody
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthBody
import com.atef.clubhouse.data.remote.feature.auth.model.UpdateUsernameBody
import com.atef.clubhouse.data.remote.feature.auth.model.UpdateNameBody
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiHandler {

    @POST("start_phone_number_auth")
    suspend fun startPhoneNumberAuth(@Body startPhoneNumberAuthBody: StartPhoneNumberAuthBody): Result<BaseResponse>

    @POST("resend_phone_number_auth")
    suspend fun resendVerificationCode(@Body resendVerificationCodeBody: ResendVerificationCodeBody): Result<BaseResponse>

    @POST("complete_phone_number_auth")
    suspend fun completePhoneNumberAuth(
        @Body completePhoneNumberAuthBody: CompletePhoneNumberAuthBody,
    ): Result<CompletePhoneNumberAuthResponse>

    @POST("update_username")
    suspend fun updateUsername(@Body updateUsernameBody: UpdateUsernameBody): Result<BaseResponse>

    @POST("update_name")
    suspend fun updateName(@Body updateNameBody: UpdateNameBody): Result<BaseResponse>
}

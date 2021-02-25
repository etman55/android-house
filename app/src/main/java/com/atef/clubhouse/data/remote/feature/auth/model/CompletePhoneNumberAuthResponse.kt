package com.atef.clubhouse.data.remote.feature.auth.model

import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class CompletePhoneNumberAuthResponse(
    @SerializedName("auth_token")
    val authToken: String? = null,
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("refresh_token")
    val refreshToken: String? = null,
    @SerializedName("is_waitlisted")
    val isWaitListed: Boolean? = null,
    @SerializedName("is_onboarding")
    val isOnBoarding: Boolean? = null,
    @SerializedName("is_verified")
    val isVerified: Boolean? = null,
    @SerializedName("user_profile")
    val user: ProfileResponse? = null,
) : BaseResponse()

package com.atef.clubhouse.data.remote.feature.auth.model

import com.google.gson.annotations.SerializedName

data class ResendVerificationCodeBody(
    @SerializedName("phone_number")
    val phoneNumber: String
)

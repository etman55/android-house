package com.atef.clubhouse.data.remote.feature.auth.model

import com.google.gson.annotations.SerializedName

data class CompletePhoneNumberAuthBody(
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("verification_code")
    val verificationCode: String
)

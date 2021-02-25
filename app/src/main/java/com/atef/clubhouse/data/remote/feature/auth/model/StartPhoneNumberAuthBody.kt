package com.atef.clubhouse.data.remote.feature.auth.model

import com.google.gson.annotations.SerializedName

data class StartPhoneNumberAuthBody(
    @SerializedName("phone_number")
    val phoneNumber: String
)
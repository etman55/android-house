package com.atef.clubhouse.data.remote.feature.auth.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProfileResponse(
    @SerializedName("user_id")
    val userId: Int,
    val name: String? = null,
    @SerializedName("photo_url")
    val photoUrl: String? = null,
    val username: String? = null
)

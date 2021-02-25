package com.atef.clubhouse.data.remote.base.model

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("error_message")
    val errorMessage: String? = null
    val success: Boolean? = null
}

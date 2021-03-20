package com.atef.clubhouse.data.remote.feature.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RuleResponse(
        val desc : String,
        val title : String
)

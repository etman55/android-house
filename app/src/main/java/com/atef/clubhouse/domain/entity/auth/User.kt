package com.atef.clubhouse.domain.entity.auth

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class User(
    val userId: Int,
    val name: String? = null,
    val photoUrl: String? = null,
    val username: String? = null,
    val isWaitListed: Boolean? = null,
    val isVerified: Boolean? = null,
    val isOnBoarding: Boolean? = null
) : Parcelable

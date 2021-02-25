package com.atef.clubhouse.domain.entity.country

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Country(
    val name: String,
    val dialCode: String,
    val code: String,
    val emojii: String,
) : Parcelable
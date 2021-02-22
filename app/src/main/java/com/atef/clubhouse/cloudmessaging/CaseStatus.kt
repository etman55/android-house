package com.atef.clubhouse.cloudmessaging

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
sealed class CaseStatus : Parcelable {

    companion object {
        fun readStatus(s: String?): CaseStatus? {
            return when (s) {
                "Status1" -> Status1
                "Status2" -> Status2
                "Status3" -> Status3
                "Status4" -> Status4
                else -> null
            }
        }
    }

    @Parcelize
    object Status1 : CaseStatus()

    @Parcelize
    object Status2 : CaseStatus()

    @Parcelize
    object Status3 : CaseStatus()

    @Parcelize
    object Status4 : CaseStatus()
}

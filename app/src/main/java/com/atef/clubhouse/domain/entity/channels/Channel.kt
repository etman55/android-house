package com.atef.clubhouse.domain.entity.channels

import androidx.annotation.Keep
import com.atef.clubhouse.domain.entity.auth.User

@Keep
data class Channel(
        val creatorUserProfileId: Int,
        val channelId: Int,
        val channel: String?,
        val topic: String?,
        val isPrivate: Boolean,
        val isSocialMode: Boolean,
        val url: String?,
        val clubName: String?,
        val clubId: Int,
        val welcomeForUserProfile: String?,
        val numOther: Int,
        val hasBlockedSpeakers: Boolean,
        val isExploreChannel: Boolean,
        val numSpeakers: Int,
        val numAll: Int,
        val users: List<User>

)

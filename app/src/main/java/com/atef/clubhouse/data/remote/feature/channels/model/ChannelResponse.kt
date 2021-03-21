package com.atef.clubhouse.data.remote.feature.channels.model

import androidx.annotation.Keep
import com.atef.clubhouse.data.remote.feature.auth.model.ProfileResponse
import com.google.gson.annotations.SerializedName

@Keep
data class ChannelResponse(
        @SerializedName("creator_user_profile_id")
        val creatorUserProfileId: Int,
        @SerializedName("channel_id")
        val channelId: Int,
        val channel: String?,
        val topic: String?,
        @SerializedName("is_private")
        val isPrivate: Boolean,
        @SerializedName("is_social_mode")
        val isSocialMode: Boolean,
        val url: String?,
        val club: ClubResponse,
        @SerializedName("club_name")
        val clubName: String?,
        @SerializedName("club_id")
        val clubId: Int,
        @SerializedName("welcome_for_user_profile")
        val welcomeForUserProfile: String?,
        @SerializedName("num_other")
        val numOther: Int,
        @SerializedName("has_blocked_speakers")
        val hasBlockedSpeakers: Boolean,
        @SerializedName("is_explore_channel")
        val isExploreChannel: Boolean,
        @SerializedName("num_speakers")
        val numSpeakers: Int,
        @SerializedName("num_all")
        val numAll: Int,
        val users: List<ProfileResponse>

)

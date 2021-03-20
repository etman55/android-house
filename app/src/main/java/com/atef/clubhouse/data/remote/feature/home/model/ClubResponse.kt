package com.atef.clubhouse.data.remote.feature.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ClubResponse(
        @SerializedName("club_id")
        val clubId : Int,
        val name : String,
        val description : String,
        @SerializedName("photo_url")
        val photoUrl : String,
        @SerializedName("num_members")
        val numMembers : Int,
        @SerializedName("num_followers")
        val numFollowers : Int,
        @SerializedName("enable_private")
        val enablePrivate : Boolean,
        @SerializedName("is_follow_allowed")
        val isFollowAllowed : Boolean,
        @SerializedName("is_membership_private")
        val isMembershipPrivate : Boolean,
        @SerializedName("is_community")
        val isCommunity : Boolean,
        val rules : List<RuleResponse>,
        @SerializedName("num_online")
        val numOnline : Int
)

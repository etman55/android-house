package com.atef.clubhouse.data.remote.feature.channels.mapper

import com.atef.clubhouse.data.base.mapper.RemoteModelMapper
import com.atef.clubhouse.data.remote.feature.auth.mapper.ProfileMapper
import com.atef.clubhouse.data.remote.feature.channels.model.ChannelResponse
import com.atef.clubhouse.domain.entity.channels.Channel
import javax.inject.Inject

class ChannelMapper @Inject constructor(private val listMapper: ProfileMapper) : RemoteModelMapper<ChannelResponse, Channel> {
    override fun mapFromModel(model: ChannelResponse): Channel {
        return with(model) {
            Channel(channelId = channelId!!,
                    channel = channel,
                    url = url,
                    topic = topic,
                    isPrivate = isPrivate,
                    numSpeakers = numSpeakers,
                    numAll = numAll,
                    numOther = numOther,
                    clubId = clubId,
                    clubName = clubName,
                    creatorUserProfileId = creatorUserProfileId,
                    hasBlockedSpeakers = hasBlockedSpeakers,
                    isExploreChannel = isExploreChannel,
                    isSocialMode = isSocialMode,
                    welcomeForUserProfile = welcomeForUserProfile,
                    users = listMapper.mapModelList(users)
            )
        }
    }
}
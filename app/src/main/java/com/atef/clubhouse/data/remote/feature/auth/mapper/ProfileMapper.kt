package com.atef.clubhouse.data.remote.feature.auth.mapper

import com.atef.clubhouse.data.base.mapper.RemoteModelMapper
import com.atef.clubhouse.data.remote.feature.auth.model.ProfileResponse
import com.atef.clubhouse.domain.entity.auth.User
import javax.inject.Inject

class ProfileMapper @Inject constructor() : RemoteModelMapper<ProfileResponse, User> {
    override fun mapFromModel(model: ProfileResponse): User {
        return with(model) {
            User(userId = userId,
                name = name,
                photoUrl = photoUrl,
                username = username,
                isWaitListed = false,
                isVerified = true,
                isOnBoarding = true
            )
        }
    }
}
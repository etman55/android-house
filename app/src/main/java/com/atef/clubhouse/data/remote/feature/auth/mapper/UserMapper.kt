package com.atef.clubhouse.data.remote.feature.auth.mapper

import com.atef.clubhouse.data.base.mapper.RemoteModelMapper
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.domain.entity.auth.User
import javax.inject.Inject

class UserMapper @Inject constructor() : RemoteModelMapper<CompletePhoneNumberAuthResponse, User> {
    override fun mapFromModel(model: CompletePhoneNumberAuthResponse): User {
        return with(model) {
            User(userId = user?.userId!!,
                name = user.name,
                photoUrl = user.photoUrl,
                username = user.username,
                isWaitListed = isWaitListed,
                isVerified = isVerified,
                isOnBoarding = isOnBoarding
            )
        }
    }
}
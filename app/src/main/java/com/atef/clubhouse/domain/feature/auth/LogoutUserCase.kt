package com.atef.clubhouse.domain.feature.auth

import com.atef.clubhouse.data.local.feature.auth.AuthLocalDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LogoutUserCase @Inject constructor(private val authLocalDataSourceImpl: AuthLocalDataSourceImpl) {

    operator fun invoke() {
        authLocalDataSourceImpl.logout()
    }
}
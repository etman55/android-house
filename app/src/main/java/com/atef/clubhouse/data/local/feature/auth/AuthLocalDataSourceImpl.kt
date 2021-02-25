package com.atef.clubhouse.data.local.feature.auth

import com.atef.clubhouse.data.feature.auth.AuthLocalDataSource
import com.atef.clubhouse.data.local.PreferencesGateway
import com.atef.clubhouse.data.local.PreferencesGateway.Companion.DEFAULT_VALUE
import com.atef.clubhouse.data.local.fromObjectToString
import com.atef.clubhouse.data.local.fromStringToObject
import com.atef.clubhouse.domain.entity.auth.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class AuthLocalDataSourceImpl @Inject constructor(private val prefs: PreferencesGateway) : AuthLocalDataSource {
    private val userChannel = ConflatedBroadcastChannel<User?>()
    override val user: Flow<User?> = userChannel.asFlow()

    private val tokenChannel = ConflatedBroadcastChannel<String>()
    override val token: Flow<String> = tokenChannel.asFlow()

    private val isWaitListedChannel = ConflatedBroadcastChannel<Boolean>()
    override val isWaitListed: Flow<Boolean> = isWaitListedChannel.asFlow()

    init {
        tokenChannel.offer(prefs.load(TOKEN_KEY, DEFAULT_VALUE))
        val user: User? = prefs.load(USER_KEY, DEFAULT_VALUE).fromStringToObject<User>(User::class.java)
        userChannel.offer(user)
        user?.isWaitListed?.let { isWaitListedChannel.offer(it) }
    }

    override suspend fun saveToken(authToken: String) {
        prefs.save(TOKEN_KEY, authToken)
    }

    override suspend fun saveUser(user: User) {
        prefs.save(USER_KEY, user.fromObjectToString(User::class.java))
    }

    override fun logout() {
        prefs.remove(TOKEN_KEY)
        prefs.remove(USER_KEY)
    }

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
        const val USER_KEY = "USER_KEY"
    }
}
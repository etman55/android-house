package com.atef.clubhouse.data.feature.auth

import com.atef.clubhouse.domain.entity.auth.User
import kotlinx.coroutines.flow.Flow


interface AuthLocalDataSource {
    val user: Flow<User?>
    val token: Flow<String>
    val isWaitListed: Flow<Boolean>
    suspend fun saveToken(authToken: String)
    suspend fun saveUser(user: User)

    fun logout()
}
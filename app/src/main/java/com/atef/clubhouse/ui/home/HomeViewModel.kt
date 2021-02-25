package com.atef.clubhouse.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.atef.clubhouse.base.BaseViewModel
import com.atef.clubhouse.base.extension.mutable
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.feature.auth.LogoutUserCase
import com.atef.clubhouse.domain.feature.auth.UserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase,
    private val logoutUserCase: LogoutUserCase,
) : BaseViewModel<HomeNavigation>() {

    val user = mutable<User?>()

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            userUseCase().collect {
                user.postValue(it)
            }
        }
    }

    fun navigateToLogout() {
        logoutUserCase()
        navigationEvent.postValue(HomeNavigation.Logout)
    }

}
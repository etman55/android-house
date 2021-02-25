package com.atef.clubhouse.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.atef.clubhouse.base.BaseViewModel
import com.atef.clubhouse.base.extension.mutable
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.feature.auth.UserUseCase
import com.atef.clubhouse.domain.feature.warning.GetWarningUseCase
import com.atef.clubhouse.domain.feature.warning.WarningUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase,
    private val warningUseCase: WarningUseCase,
    private val getWarningUseCase: GetWarningUseCase,
) : BaseViewModel<MainNavigation>() {

    val user = mutable<User?>()
    val warning = mutable<Boolean>()

    init {
        warning.postValue(getWarningUseCase())
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            userUseCase().collect {
                user.postValue(it)
            }
        }
    }

    fun setShowWarning(show: Boolean) {
        warningUseCase(show)
    }

    fun navigateToLogin() {
        navigationEvent.postValue(MainNavigation.Login)
    }

    fun navigateToWaiting() {
        navigationEvent.postValue(MainNavigation.Waiting)
    }

    fun navigateToHome() {
        navigationEvent.postValue(MainNavigation.Home)
    }
}
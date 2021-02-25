package com.atef.clubhouse.ui.waiting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.atef.clubhouse.base.BaseViewModel
import com.atef.clubhouse.base.extension.mutable
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.feature.auth.LogoutUserCase
import com.atef.clubhouse.domain.feature.auth.UserUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class WaitedListViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase,
    private val logoutUserCase: LogoutUserCase,
) : BaseViewModel<WaitListedNavigation>() {

    val user = mutable<User?>()

    init {
        viewModelScope.launch {
            userUseCase().collect {
                user.postValue(it)
            }
        }
    }

    fun navigateToLogout() {
        logoutUserCase()
        navigationEvent.postValue(WaitListedNavigation.Logout)
    }

    fun navigateToHome() {
        navigationEvent.postValue(WaitListedNavigation.Home)
    }


}
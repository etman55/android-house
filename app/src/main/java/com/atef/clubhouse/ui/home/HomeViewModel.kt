package com.atef.clubhouse.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.atef.clubhouse.base.BaseViewModel
import com.atef.clubhouse.base.Resource
import com.atef.clubhouse.base.extension.mutable
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.entity.home.Channel
import com.atef.clubhouse.domain.feature.auth.LogoutUserCase
import com.atef.clubhouse.domain.feature.auth.UserUseCase
import com.atef.clubhouse.domain.feature.home.GetChannelsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase,
    private val logoutUserCase: LogoutUserCase,
    private val getChannelsUseCase: GetChannelsUseCase,
) : BaseViewModel<HomeNavigation>() {

    val user = mutable<User?>()
    val channels = mutable<Resource<List<Channel>>>()

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


    fun getChannels() {
        viewModelScope.launch {
            handleResult(getChannelsUseCase(),
                    { channels.postValue(Resource.success(it)) },
                    { channels.postValue(Resource.error(msgRes = it)) },
                    { channels.postValue(Resource.error(msg = it)) })
        }
    }

    fun navigateToLogout() {
        logoutUserCase()
        navigationEvent.postValue(HomeNavigation.Logout)
    }

}
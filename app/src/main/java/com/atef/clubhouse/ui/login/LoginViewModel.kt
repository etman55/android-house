package com.atef.clubhouse.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.atef.clubhouse.base.BaseViewModel
import com.atef.clubhouse.base.Resource
import com.atef.clubhouse.base.extension.mutable
import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.domain.feature.auth.StartPhoneNumberAuthUseCase
import com.atef.clubhouse.domain.feature.country.CurrentCountryUseCase
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val currentCountryUseCase: CurrentCountryUseCase,
    private val startPhoneNumberAuthUseCase: StartPhoneNumberAuthUseCase,
) : BaseViewModel<LoginNavigation>() {

    val currentCountry = mutable<Country>()
    val startPhoneNumber = mutable<Resource<BaseResponse>>()

    init {
        viewModelScope.launch {
            currentCountry.postValue(currentCountryUseCase())
        }
    }

    fun startPhoneNumberAuth(phoneNumber: String) {
        viewModelScope.launch {
            startPhoneNumber.postValue(Resource.loading())
            handleResult(startPhoneNumberAuthUseCase(StartPhoneNumberAuthUseCase.Params(phoneNumber)),
                {
                    startPhoneNumber.postValue(Resource.success(it))
                }, {
                    startPhoneNumber.postValue(Resource.error(msgRes = it))
                }, {
                    startPhoneNumber.postValue(Resource.error(msg = it))
                })
        }
    }


    fun navigateToVerificationCode(phoneNumber: String) {
        navigationEvent.postValue(LoginNavigation.VerificationCodeNavigation(phoneNumber))
    }
}
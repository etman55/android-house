package com.atef.clubhouse.ui.verificationcode

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.atef.clubhouse.base.BaseViewModel
import com.atef.clubhouse.base.Resource
import com.atef.clubhouse.base.extension.mutable
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.domain.feature.auth.CompletePhoneNumberAuthUseCase
import com.atef.clubhouse.domain.feature.auth.ResendVerificationCodeUseCase
import kotlinx.coroutines.launch

class VerificationCodeViewModel @ViewModelInject constructor(
    private val resendVerificationCodeUseCase: ResendVerificationCodeUseCase,
    private val completePhoneNumberAuthUseCase: CompletePhoneNumberAuthUseCase,
) : BaseViewModel<VerificationCodeNavigation>() {

    val resendVerificationCode = mutable<Resource<Unit>>()
    val completePhoneAuth = mutable<Resource<CompletePhoneNumberAuthResponse>>()

    fun completeAuth(phoneNumber: String, verificationCode: String) {
        viewModelScope.launch {
            handleResult(completePhoneNumberAuthUseCase(CompletePhoneNumberAuthUseCase.Params(phoneNumber,
                verificationCode)),
                { completePhoneAuth.postValue(Resource.success(it)) },
                { completePhoneAuth.postValue(Resource.error(msgRes = it)) },
                { completePhoneAuth.postValue(Resource.error(msg = it)) })
        }
    }

    fun resendCode(phoneNumber: String) {
        viewModelScope.launch {
            handleResult(resendVerificationCodeUseCase(ResendVerificationCodeUseCase.Params(phoneNumber)),
                { resendVerificationCode.postValue(Resource.success(Unit)) },
                { resendVerificationCode.postValue(Resource.error(msgRes = it)) },
                { resendVerificationCode.postValue(Resource.error(msg = it)) })
        }
    }

    fun navigationBack() {
        navigationEvent.postValue(VerificationCodeNavigation.Back)
    }

    fun navigateHome() {
        navigationEvent.postValue(VerificationCodeNavigation.Home)
    }

    fun navigateToWaitListed() {
        navigationEvent.postValue(VerificationCodeNavigation.WaitListed)
    }
}
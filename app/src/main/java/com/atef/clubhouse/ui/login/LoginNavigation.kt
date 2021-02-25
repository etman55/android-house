package com.atef.clubhouse.ui.login

import com.atef.clubhouse.base.Navigation

sealed class LoginNavigation : Navigation {
    data class VerificationCodeNavigation(val phoneNumber: String) : LoginNavigation()
}
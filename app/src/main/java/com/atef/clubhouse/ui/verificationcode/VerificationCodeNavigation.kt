package com.atef.clubhouse.ui.verificationcode

import com.atef.clubhouse.base.Navigation

sealed class VerificationCodeNavigation : Navigation {
    object Back : VerificationCodeNavigation()
    object Home : VerificationCodeNavigation()
    object WaitListed : VerificationCodeNavigation()
}

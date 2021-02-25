package com.atef.clubhouse.ui.waiting

import com.atef.clubhouse.base.Navigation

sealed class WaitListedNavigation : Navigation {
    object Home : WaitListedNavigation()
    object Logout : WaitListedNavigation()
}

package com.atef.clubhouse.ui.home

import com.atef.clubhouse.base.Navigation

sealed class HomeNavigation : Navigation {
    object Logout : HomeNavigation()
}

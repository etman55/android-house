package com.atef.clubhouse.ui.main

import com.atef.clubhouse.base.Navigation

sealed class MainNavigation : Navigation {
    object Home : MainNavigation()
    object Waiting : MainNavigation()
    object Login : MainNavigation()
}

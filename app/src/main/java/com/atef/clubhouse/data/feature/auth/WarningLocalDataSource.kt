package com.atef.clubhouse.data.feature.auth

interface WarningLocalDataSource {
    fun shouldShowWarning(show: Boolean)
    fun getShowWarning(): Boolean
}
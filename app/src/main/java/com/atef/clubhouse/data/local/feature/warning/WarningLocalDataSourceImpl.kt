package com.atef.clubhouse.data.local.feature.warning

import com.atef.clubhouse.data.feature.auth.WarningLocalDataSource
import com.atef.clubhouse.data.local.PreferencesGateway
import javax.inject.Inject

class WarningLocalDataSourceImpl @Inject constructor(
    private val preferencesGateway: PreferencesGateway,
) : WarningLocalDataSource {
    override fun shouldShowWarning(show: Boolean) {
        preferencesGateway.save(SHOULD_SHOW_WARNING, show)
    }

    override fun getShowWarning(): Boolean {
        return preferencesGateway.load(SHOULD_SHOW_WARNING, true)
    }

    companion object {
        const val SHOULD_SHOW_WARNING = "SHOULD_SHOW_WARNING"
    }
}
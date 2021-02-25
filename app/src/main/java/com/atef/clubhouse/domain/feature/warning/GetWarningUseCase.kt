package com.atef.clubhouse.domain.feature.warning

import com.atef.clubhouse.data.feature.auth.WarningLocalDataSource
import javax.inject.Inject

class GetWarningUseCase @Inject constructor(private val warningLocalDataSource: WarningLocalDataSource) {

    operator fun invoke(): Boolean {
        return warningLocalDataSource.getShowWarning()
    }
}
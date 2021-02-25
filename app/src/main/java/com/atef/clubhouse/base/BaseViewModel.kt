package com.atef.clubhouse.base

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.atef.clubhouse.R
import com.atef.clubhouse.data.remote.base.model.Result
import com.atef.clubhouse.base.extension.event
import com.atef.clubhouse.data.remote.base.errorhandling.ErrorEntity

open class BaseViewModel<N : Navigation> @ViewModelInject constructor() : ViewModel(), LifecycleObserver {

    protected val navigationEvent = event<N>()

    fun observeNavigation(owner: LifecycleOwner, observer: (N) -> Unit) =
        navigationEvent.observe(owner, observer)

    protected inline fun <T> handleResult(
        result: Result<T>,
        onSuccess: (T) -> Unit = {},
        onError: (Int) -> Unit = {},
        onValidationError: (message: String?) -> Unit = {},
    ) {
        when (result) {
            is Result.Success -> onSuccess(result.data)
            is Result.Error -> when (result.error) {
                ErrorEntity.AccessDenied -> onError(R.string.access_denied)
                ErrorEntity.Network -> onError(R.string.network_error)
                ErrorEntity.NotFound -> onError(R.string.not_found)
                ErrorEntity.ServiceError -> onError(R.string.service_error)
                ErrorEntity.ServiceUnavailable -> onError(R.string.server_unavailable)
                ErrorEntity.UnAuthorized -> onError(R.string.unauthorized_error)
                ErrorEntity.Unknown -> onError(R.string.unknown_error)

            }
            is Result.Validation -> onValidationError(result.message)
        }
    }
}

package com.atef.clubhouse.base

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.atef.clubhouse.R
import com.atef.clubhouse.base.extension.event
import com.atef.clubhouse.data.remote.base.calladapter.NetworkResponse

open class BaseViewModel<N : Navigation> @ViewModelInject constructor() : ViewModel(), LifecycleObserver {

    protected val navigationEvent = event<N>()

    fun observeNavigation(owner: LifecycleOwner, observer: (N) -> Unit) =
        navigationEvent.observe(owner, observer)

    protected inline fun <T : Any, U : Any> handelNetworkResponse(
        response: NetworkResponse<T, U>,
        onSuccess: (T) -> Unit = {},
        onError: (U) -> Unit = {},
        errorMsg: (Int) -> Unit = {}
    ) {
        when (response) {
            is NetworkResponse.Success -> onSuccess(response.body)
            is NetworkResponse.ApiError -> onError(response.body)
            is NetworkResponse.NetworkError -> errorMsg(R.string.network_error)
            is NetworkResponse.UnknownError -> errorMsg(R.string.unknown_error)
        }
    }
}

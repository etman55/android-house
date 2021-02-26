package com.atef.clubhouse.base

import android.content.Context
import com.atef.clubhouse.utils.NetworkStateDialog

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val messageRes: Int?,
) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msgRes: Int? = null, msg: String? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg, msgRes)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null, null)
        }
    }
}

enum class Status {
    LOADING,
    ERROR,
    SUCCESS
}

inline fun <reified T : Any> Resource<T>.handleResource(
    context: Context,
    onLoading: () -> Unit = {},
    onSuccess: (T?) -> Unit = {},
    onError: (String?, Int?) -> Unit,
) {
    when (this.status) {
        Status.LOADING -> {
            NetworkStateDialog.show(context)
            onLoading()
        }
        Status.SUCCESS -> {
            NetworkStateDialog.dismiss()
            onSuccess(this.data)
        }
        Status.ERROR -> {
            NetworkStateDialog.dismiss()
            onError(this.message, this.messageRes)
        }
    }
}

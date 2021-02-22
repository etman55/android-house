package com.atef.clubhouse.base

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val messageRes: Int?
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

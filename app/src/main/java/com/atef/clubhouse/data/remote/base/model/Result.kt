package com.atef.clubhouse.data.remote.base.model

import com.atef.clubhouse.data.remote.base.errorhandling.ErrorEntity


sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: ErrorEntity) : Result<T>()
    data class Validation<T>(val message: String?) : Result<T>()

    fun <R> map(transform: (type: T) -> R): Result<R> =
        when (this) {
            is Success<T> -> Success(transform(this.data))
            is Error -> Error(this.error)
            is Validation -> Validation(this.message)
        }

}

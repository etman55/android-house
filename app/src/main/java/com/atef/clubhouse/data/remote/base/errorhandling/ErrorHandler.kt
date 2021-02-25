package com.atef.clubhouse.data.remote.base.errorhandling


interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}
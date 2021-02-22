package com.atef.clubhouse.utils

val Throwable.errorMessage: String
    get() = message ?: localizedMessage ?: "An error occurred 😩"

package com.atef.clubhouse.data.remote.base.errorhandling



sealed class ErrorEntity {
    object Network : ErrorEntity()
    object NotFound : ErrorEntity()
    object UnAuthorized : ErrorEntity()
    object AccessDenied : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object ServiceError : ErrorEntity()
    object Unknown : ErrorEntity()
}
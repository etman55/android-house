package com.atef.clubhouse.data.remote.base.calladapter

import com.atef.clubhouse.data.remote.base.errorhandling.ErrorHandlerImpl
import com.atef.clubhouse.data.remote.base.model.Result
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCoroutinesCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {

        check(returnType is ParameterizedType) { "$returnType must be parameterized. Raw types are not supported" }

        val containerType = getParameterUpperBound(0, returnType)
        if (getRawType(containerType) != Result::class.java) {
            return null
        }

        check(containerType is ParameterizedType) { "$containerType must be parameterized. Raw types are not supported" }
        val successType = getParameterUpperBound(0, containerType)
        val errorBodyConverter = ErrorHandlerImpl()

        return when (getRawType(returnType)) {
            Call::class.java -> {
                ResultCoroutinesAdapter<Any>(
                    successType,
                    errorBodyConverter
                )
            }
            else -> null
        }
    }

}
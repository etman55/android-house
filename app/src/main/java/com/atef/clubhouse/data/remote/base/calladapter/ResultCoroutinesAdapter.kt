package com.atef.clubhouse.data.remote.base.calladapter

import com.atef.clubhouse.data.remote.base.errorhandling.ErrorHandler
import com.atef.clubhouse.data.remote.base.model.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type


class ResultCoroutinesAdapter<T>(
    private val type: Type,
    private val errorHandler: ErrorHandler,
) : CallAdapter<T, Call<Result<T>>> {

    override fun responseType(): Type = type

    override fun adapt(call: Call<T>): Call<Result<T>> =
        ResultCoroutinesCall(
            call,
            errorHandler
        )

}
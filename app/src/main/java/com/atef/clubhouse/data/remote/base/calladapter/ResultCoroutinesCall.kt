package com.atef.clubhouse.data.remote.base.calladapter

import com.atef.clubhouse.data.remote.base.errorhandling.ErrorEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.atef.clubhouse.data.remote.base.errorhandling.ErrorHandler
import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.data.remote.base.model.Result
import com.google.gson.Gson
import okhttp3.Request
import okio.Timeout
import retrofit2.HttpException

class ResultCoroutinesCall<T>(
    private val proxy: Call<T>,
    private val errorHandler: ErrorHandler,
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) = proxy.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val body = response.body()
            val error = response.errorBody()
            if (response.isSuccessful) {
                if (body != null) {
                    callback.onResponse(
                        this@ResultCoroutinesCall,
                        Response.success(Result.Success(body))
                    )
                } else {
                    // Response is successful but the body is null
                    callback.onResponse(
                        this@ResultCoroutinesCall,
                        Response.success(Result.Error(ErrorEntity.Unknown))
                    )
                }
            } else {
                // generic error handling
                if (error != null) {
                    val errorBody = Gson().fromJson(
                        error.charStream(),
                        BaseResponse::class.java
                    )
                    errorBody.errorMessage?.let {
                        val result: Result<T> = Result.Validation(it)
                        callback.onResponse(
                            this@ResultCoroutinesCall,
                            Response.success(result)
                        )
                    } ?: run {
                        callback.onResponse(
                            this@ResultCoroutinesCall,
                            Response.success(
                                Result.Error(errorHandler.getError(HttpException(response)))
                            )
                        )
                    }
                } else {
                    callback.onResponse(
                        this@ResultCoroutinesCall,
                        Response.success(Result.Error(ErrorEntity.Unknown))
                    )
                }
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val result: Result<T> = Result.Error(errorHandler.getError(t))
            callback.onResponse(this@ResultCoroutinesCall, Response.success(result))
        }
    })

    override fun isExecuted() = proxy.isExecuted

    override fun clone() =
        ResultCoroutinesCall(proxy.clone(), errorHandler)

    override fun isCanceled() = proxy.isCanceled

    override fun cancel() = proxy.cancel()

    override fun execute(): Response<Result<T>> {
        throw UnsupportedOperationException("Result doesn't support execute")
    }

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()
}
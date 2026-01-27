package com.pessoto.ituneslist.core.data.source.remote

sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Failure(val error: Throwable) : DataResult<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): DataResult<T> {
    return try {
        DataResult.Success(apiCall())
    } catch (e: Exception) {
        DataResult.Failure(e)
    }
}

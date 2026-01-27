package com.pessoto.ituneslist.core.data.repository

import com.pessoto.ituneslist.core.data.source.remote.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <R, T> runFlow(
    handleError: (Throwable) -> Throwable = { it },
    map: (R) -> T,
    invoke: suspend () -> DataResult<R>,
): Flow<T> = flow {
    when (val result = invoke()) {
        is DataResult.Failure -> throw handleError(result.error)
        is DataResult.Success -> emit(map(result.data))
    }
}

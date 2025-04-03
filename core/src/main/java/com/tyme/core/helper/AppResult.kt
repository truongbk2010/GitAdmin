package com.tyme.core.helper

sealed class AppResult<out T> {
    data class Success<out T>(
        val value: T,
    ) : AppResult<T>()

    data class Error(
        val error: AppError,
    ) : AppResult<Nothing>()
}

fun <T, U> AppResult<T>.map(transform: (T) -> U): AppResult<U> =
    when (val data = this) {
        is AppResult.Success -> {
            AppResult.Success(transform(data.value))
        }

        is AppResult.Error -> {
            AppResult.Error(data.error)
        }
    }

fun <T> AppResult<T>.value(): T? =
    when (val data = this) {
        is AppResult.Success -> data.value
        else -> null
    }

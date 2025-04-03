package com.tyme.core.helper

sealed class AppError(
    val message: String? = null,
) {
    // Data Error entity
    class NetworkError(
        message: String,
    ) : AppError(message)

    class DatabaseError(
        message: String,
    ) : AppError(message)

    sealed class ApiError : AppError()
}

fun Throwable.toDatabaseError(): AppError.DatabaseError = AppError.DatabaseError(this.message ?: "")

fun Throwable.toNetworkError(): AppError.NetworkError = AppError.NetworkError(this.message ?: "")

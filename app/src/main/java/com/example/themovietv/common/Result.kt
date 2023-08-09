package com.example.themovietv.common

import com.example.themovietv.common.data.exception.DomainException

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val domainException: DomainException) : Result<T>()
}

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (domainException: DomainException) -> R
): R = when (this) {
    is Result.Success -> onSuccess(data)
    is Result.Failure -> onFailure(domainException)
}

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R
): R? = when (this) {
    is Result.Success -> onSuccess(data)
    is Result.Failure -> null
}

fun <T> Result<T>.isSuccess(): Boolean = when (this) {
    is Result.Success -> true
    is Result.Failure -> false
}

fun <T> Result<T>.isFailure(): Boolean = when (this) {
    is Result.Failure -> true
    is Result.Success -> false
}

fun <T> Result<T>.getFailure() = when (this) {
    is Result.Failure -> domainException
    else -> null
}

fun <T> Result<T>.getSuccess() = when (this) {
    is Result.Success -> data
    else -> null
}

package com.example.themovietv.common.data.exception

fun interface DomainExceptionRepository {

    fun manageError(error: Throwable): DomainException
}

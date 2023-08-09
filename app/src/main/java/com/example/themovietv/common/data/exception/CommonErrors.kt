package com.example.themovietv.common.data.exception

import java.net.ConnectException
import java.net.SocketTimeoutException
import org.json.JSONException

open class CommonErrors {

    fun manageException(throwable: Throwable): DomainException {
        return manageJavaErrors(throwable)
    }

    private fun manageJavaErrors(throwable: Throwable): DomainException {
        return when (throwable) {
            is SocketTimeoutException -> TimeOutException
            is ConnectException -> InternalErrorException
            else -> manageParsingExceptions(throwable)
        }
    }

    private fun manageParsingExceptions(throwable: Throwable): DomainException {
        return when (throwable) {
            is JSONException -> ParseException
            else -> manageOtherException(throwable)
        }
    }

    private fun manageOtherException(throwable: Throwable): DomainException {
        return when (throwable) {
            is NoConnectivityException -> NoConnectivityDomainException
            else -> UnknownError
        }
    }
}

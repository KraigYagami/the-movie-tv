package com.example.themovietv.common.data.exception

import java.io.IOException

open class DomainException(override val message: String = "") : Throwable(message)
object NotFoundException : DomainException()
object BadRequestException : DomainException()
object InternalErrorException : DomainException()
object UnknownError : DomainException()
object NoConnectivityException : IOException()
object NoConnectivityDomainException : DomainException()
object TimeOutException : DomainException()
object ParseException : DomainException()
object UnknownUser : DomainException()
object Unauthorized : DomainException()
object RegistrationTokenFailed : DomainException()
object LogoutWithActiveServiceException : DomainException()
object GetStorageAvailableListException : DomainException()
object KeyDataStoreNotFoundException : DomainException()
data class HttpErrorCode(val code: Int, override val message: String) : DomainException()

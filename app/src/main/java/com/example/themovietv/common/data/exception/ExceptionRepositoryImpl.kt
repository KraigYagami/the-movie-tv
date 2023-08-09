package com.example.themovietv.common.data.exception

import retrofit2.HttpException

class ExceptionRepositoryImpl : CommonErrors(), DomainExceptionRepository {
    override fun manageError(error: Throwable): DomainException {
        return if (error is HttpException) {
            HttpErrors.getHttpError(error)
        } else {
            manageException(error)
        }
    }
}

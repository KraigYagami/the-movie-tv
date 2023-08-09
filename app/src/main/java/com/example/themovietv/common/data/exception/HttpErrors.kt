package com.example.themovietv.common.data.exception

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.net.ssl.HttpsURLConnection
import retrofit2.HttpException

object HttpErrors {

    private const val EMPTY = ""
    private const val JSON_EMPTY = "{}"

    private val moshi = Moshi.Builder().build()

    private val httpErrors = mapOf(
        HttpsURLConnection.HTTP_BAD_REQUEST to BadRequestException,
        HttpsURLConnection.HTTP_NOT_FOUND to NotFoundException,
        HttpsURLConnection.HTTP_INTERNAL_ERROR to InternalErrorException,
        HttpsURLConnection.HTTP_UNAUTHORIZED to Unauthorized,
        422 to Unauthorized
    )

    fun getHttpError(error: HttpException): DomainException {
        return if (httpErrors.containsKey(error.code())) {
            httpErrors.getValue(error.code())
        } else {
            HttpErrorCode(error.code(), getMessage(error).message)
        }
    }

    private fun getMessage(exception: HttpException): DomainException {
        return try {
            var jsonString = exception.response()?.errorBody()?.string()
            if (jsonString.isNullOrEmpty()) jsonString = JSON_EMPTY
            val adapter: JsonAdapter<DomainException> = moshi.adapter(DomainException::class.java)
            adapter.fromJson(jsonString)!!
        } catch (exception: Exception) {
            DomainException(EMPTY)
        }
    }
}

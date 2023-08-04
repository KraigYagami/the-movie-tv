package com.example.themovietv.common.di

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    // TODO: Move this to a secure place
    private val apiKey =
        "***REMOVED***" // ktlint-disable max-line-length

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                addHeader("accept", "application/json")
                addHeader("Authorization", "Bearer $apiKey")
            }.build()
        )
    }
}

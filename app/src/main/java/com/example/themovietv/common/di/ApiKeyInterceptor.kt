package com.example.themovietv.common.di

import android.util.Log
import com.example.themovietv.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    private val apiKey = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("API_KEY", "apiKey: $apiKey")
        return chain.proceed(
            chain.request().newBuilder().apply {
                addHeader("accept", "application/json")
                addHeader("Authorization", "Bearer $apiKey")
            }.build()
        )
    }
}

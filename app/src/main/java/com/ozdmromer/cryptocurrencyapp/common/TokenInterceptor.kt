package com.ozdmromer.cryptocurrencyapp.common

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class TokenInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(modifiedRequest)
    }

    companion object {

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(""))
            .build()
    }
}
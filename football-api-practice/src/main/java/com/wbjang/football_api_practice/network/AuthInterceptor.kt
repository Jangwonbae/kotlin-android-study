package com.wbjang.football_api_practice.network

import com.wbjang.football_api_practice.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Auth-Token", BuildConfig.FOOTBALL_API_TOKEN)
            .build()
        return chain.proceed(request)
    }
}
package com.thejan.otrium_android.config

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", " Bearer 13ca212357363bb7239f8378cb7a312998a59fe0")
            .build()

        return chain.proceed(request)
    }
}
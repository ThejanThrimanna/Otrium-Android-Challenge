package com.thejan.otrium_android.config

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", " Bearer bd9a578fc12958c36f3bf9f58309d5f16f2c2129")
            .build()

        return chain.proceed(request)
    }
}
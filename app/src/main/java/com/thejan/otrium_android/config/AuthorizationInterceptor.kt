package com.thejan.otrium_android.config

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", " Bearer f72c2e8a5faa452fce7f5df0b88cfb8ec865c507")
            .build()

        return chain.proceed(request)
    }
}
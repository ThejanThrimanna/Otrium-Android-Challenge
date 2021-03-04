package com.thejan.otrium_android.config

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", " Bearer fc859d26750f78afee299b12f1acb9d4af2c7f7b")
            .build()

        return chain.proceed(request)
    }
}
package com.thejan.otrium_android.config

import com.thejan.otrium_android.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Should add the Personal access token from GitHub here
 */
class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + BuildConfig.TOKEN)
            .build()

        return chain.proceed(request)
    }
}
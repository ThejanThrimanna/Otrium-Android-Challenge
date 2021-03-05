package com.thejan.otrium_android.config

import android.R.attr.path
import com.thejan.otrium_android.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Should add the Personal access token from GitHub here
 */
class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = BuildConfig.TOKEN.replace("//", "")

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}
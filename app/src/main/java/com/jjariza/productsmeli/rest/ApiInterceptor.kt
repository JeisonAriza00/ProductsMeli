package com.jjariza.productsmeli.rest

import android.content.Context
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(val context: Context): Interceptor {

    lateinit var url: HttpUrl

    override fun intercept(chain: Interceptor.Chain): Response {
        url = chain.request().url
        val originalRequest = chain.request()

        return chain.proceed(originalRequest)
    }

}
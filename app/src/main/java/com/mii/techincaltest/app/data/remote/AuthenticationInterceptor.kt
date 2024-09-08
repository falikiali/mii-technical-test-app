package com.mii.techincaltest.app.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjc1OTE0MTUwLCJleHAiOjE2Nzg1MDYxNTB9.TcIgL5CDZYg9o8CUsSjUbbUdsYSaLutOWni88ZBs9S8"
        builder.addHeader("Authorization", "$token")

        return chain.proceed(builder.build())
    }
}
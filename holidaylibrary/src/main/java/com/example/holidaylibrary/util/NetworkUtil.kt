package com.example.holidaylibrary.util

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {
    const val TIME_OUT = 10L

    fun createOkHttpClient(pairApiKey: Pair<String, String>) =
        OkHttpClient().newBuilder().readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor { chain -> addApiKeyToRequests(pairApiKey, chain) }
            .addNetworkInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) })
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS).build()

    fun setupRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createRetrofitClient(
        apiInterface: Class<T>,
        retrofit: Retrofit
    ): T = retrofit.create(apiInterface)

    private fun addApiKeyToRequests(
        pairApiKey: Pair<String, String>,
        chain: Interceptor.Chain
    ): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(pairApiKey.first, pairApiKey.second).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}

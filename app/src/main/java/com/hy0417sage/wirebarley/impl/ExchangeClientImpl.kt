package com.hy0417sage.wirebarley.impl

import com.hy0417sage.wirebarley.ExchangeClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExchangeClientImpl : ExchangeClient {
    private const val BASE_URL = "https://api.apilayer.com/"

    override fun getExchangeRate(): Retrofit {
        return client
    }

    private val interceptor by lazy {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
    private val httpClient by lazy {
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
package com.hy0417sage.wirebarley.data.api

import retrofit2.Retrofit

interface ExchangeClient {
    fun getExchangeRate(): Retrofit
}
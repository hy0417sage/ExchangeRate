package com.hy0417sage.wirebarley

import retrofit2.Retrofit

interface ExchangeClient {
    fun getExchangeRate(): Retrofit
}
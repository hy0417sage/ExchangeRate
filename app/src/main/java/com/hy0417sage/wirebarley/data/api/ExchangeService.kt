package com.hy0417sage.wirebarley.data.api

import com.hy0417sage.wirebarley.data.model.Exchange
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService {
    @GET("currency_data/live?")
    fun getGitHubService(
        @Query(value = "apikey", encoded = true) to: String?,
    ): Call<Exchange>
}
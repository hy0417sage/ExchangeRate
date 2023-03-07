package com.hy0417sage.wirebarley.data

import com.hy0417sage.wirebarley.domain.ExchangeRepository
import com.hy0417sage.wirebarley.data.api.ExchangeService
import retrofit2.Retrofit
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
) : ExchangeRepository {
    private val service by lazy {
        retrofit.create(ExchangeService::class.java)
    }
    override suspend fun getQuotesData(apiKey: String) =
        service.getService(apiKey).quotes
}
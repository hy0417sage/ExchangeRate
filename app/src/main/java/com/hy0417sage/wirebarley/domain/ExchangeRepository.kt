package com.hy0417sage.wirebarley.domain

import com.hy0417sage.wirebarley.data.model.Exchange

interface ExchangeRepository {
    suspend fun getQuotesData(apiKey: String): Exchange.Quotes
}
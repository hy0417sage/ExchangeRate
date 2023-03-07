package com.hy0417sage.wirebarley.domain

import com.hy0417sage.wirebarley.data.model.Exchange
import kotlinx.coroutines.flow.StateFlow

interface ExchangeRepository {
    fun exchangeData(): StateFlow<Exchange.Quotes>
    fun setApiKey(apiKey: String)
}
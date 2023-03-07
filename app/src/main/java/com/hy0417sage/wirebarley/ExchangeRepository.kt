package com.hy0417sage.wirebarley

import kotlinx.coroutines.flow.StateFlow

interface ExchangeRepository {
    fun exchangeData(): StateFlow<Exchange.Quotes>
    fun setApiKey(apiKey: String)
}
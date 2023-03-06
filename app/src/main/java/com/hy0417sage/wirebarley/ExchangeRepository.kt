package com.hy0417sage.wirebarley

import androidx.lifecycle.LiveData

interface ExchangeRepository {
    fun exchangeData(): LiveData<Exchange.Quotes>
    fun setApiKey(apiKey: String)
}
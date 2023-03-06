package com.hy0417sage.wirebarley

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val exchangeRepository: ExchangeRepository) :
    ViewModel() {

    init {
        loadAPI()
    }

    private fun loadAPI() {
        exchangeRepository.setApiKey("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
    }

    fun getExchangeRate(): LiveData<Exchange.Quotes> {
        return exchangeRepository.exchangeData()
    }
}
package com.hy0417sage.wirebarley.presentation.view

import androidx.lifecycle.*
import com.hy0417sage.wirebarley.domain.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
) : ViewModel() {
    private val _quotes = MutableLiveData<List<Double>>()
    val quotes: LiveData<List<Double>> get() = _quotes
    private val _now = MutableLiveData<Int>(0)
    val now: LiveData<Int> get() = _now

    init {
        loadAPI()
    }

    private fun loadAPI() {
        viewModelScope.launch {
            val quotes = exchangeRepository.getQuotesData("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
            val exchangeRateList = listOf(quotes.KRW, quotes.JPY, quotes.PHP)
            _quotes.value = exchangeRateList
            _quotes.postValue(exchangeRateList)
        }
    }

    fun changeExchangeRate(which: Int) {
        _now.value = which
    }
}
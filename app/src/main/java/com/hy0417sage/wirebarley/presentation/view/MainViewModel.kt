package com.hy0417sage.wirebarley.presentation.view

import androidx.lifecycle.*
import com.hy0417sage.wirebarley.data.model.ViewData
import com.hy0417sage.wirebarley.domain.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
) : ViewModel() {

    private val _quotes = MutableLiveData<List<Double>>()
    private val _viewData = MutableLiveData<ViewData>()
    val viewData: LiveData<ViewData> get() = _viewData

    init {
        loadAPI()
    }

    private fun loadAPI() {
        viewModelScope.launch {
            val quotes = exchangeRepository.getQuotesData("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
            val exchangeRateList = listOf(quotes.KRW, quotes.JPY, quotes.PHP)
            _quotes.value = exchangeRateList
            _quotes.postValue(exchangeRateList)
            _viewData.value = ViewData(0, exchangeRateList[0], 0.0)
        }
    }

    fun exchangeRate(index: Int, reset: Double?) {
        val exchangeRate = _quotes.value?.get(index) ?: 0.0
        val total = viewData.value?.total ?: (reset ?: 0.0)
        _viewData.value = ViewData(index, exchangeRate, total)
        _viewData.postValue(ViewData(index, exchangeRate, total))
    }

    fun totalExchange(edit: String?) {
        val index = viewData.value?.nation ?: 0
        val exchangeRate = viewData.value?.exchangeRate ?: 0.0
        val total = (viewData.value?.exchangeRate ?: 0.0) * (edit?.toDouble() ?: 0.0)
        _viewData.value = ViewData(index, exchangeRate, total)
        _viewData.postValue(ViewData(index, exchangeRate, total))
    }
}
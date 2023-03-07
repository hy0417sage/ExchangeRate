package com.hy0417sage.wirebarley.presentation.view

import androidx.lifecycle.*
import com.hy0417sage.wirebarley.data.model.Exchange
import com.hy0417sage.wirebarley.domain.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
) : ViewModel() {

    init {
        loadAPI()
    }

    private val _quotes = MutableLiveData<Exchange.Quotes>()
    val quotes: LiveData<Exchange.Quotes> get() = _quotes

    private fun loadAPI(){
        viewModelScope.launch {
            val quotes = exchangeRepository.getQuotesData("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
            _quotes.value = quotes
            _quotes.postValue(quotes)
        }

    }
}
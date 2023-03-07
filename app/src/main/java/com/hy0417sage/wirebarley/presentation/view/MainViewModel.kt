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

    init {
        loadAPI()
    }

    private val _quotes = MutableLiveData<String>()
    val quotes: LiveData<String> get() = _quotes

    private fun loadAPI(){
        viewModelScope.launch {
            val quotes = exchangeRepository.getQuotesData("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
            _quotes.value = quotes.KRW.toString()
            _quotes.postValue(quotes.KRW.toString())
        }

    }
}
package com.hy0417sage.wirebarley

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.wirebarley.cache.CacheEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    private val cacheRepository: CacheRepository
) :
    ViewModel() {

    private val _cache = MutableLiveData<List<CacheEntity>>()

    init {
        loadAPI()
    }

    private fun loadAPI() {
        exchangeRepository.setApiKey("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
    }

    fun getExchangeRate(): LiveData<Exchange.Quotes> {
        return exchangeRepository.exchangeData()
    }

    fun insertCache(cache: List<CacheEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            cacheRepository.insertCache(cache)
        }
    }

    fun getCache() : LiveData<List<CacheEntity>> {
        viewModelScope.launch(Dispatchers.IO) {
            _cache.value = cacheRepository.getCache()
        }
        return _cache
    }
}
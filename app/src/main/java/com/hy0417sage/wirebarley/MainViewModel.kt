package com.hy0417sage.wirebarley

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.wirebarley.cache.CacheEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    private val cacheRepository: CacheRepository
) :
    ViewModel() {

    init {
        exchangeRepository.setApiKey("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
    }

    fun getExchangeRate(): LiveData<Exchange.Quotes> {
        return exchangeRepository.exchangeData()
    }

    fun insertCache(cache: CacheEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cacheRepository.insertCache(cache)
        }
    }

    fun getCache() = cacheRepository.getCache()

}
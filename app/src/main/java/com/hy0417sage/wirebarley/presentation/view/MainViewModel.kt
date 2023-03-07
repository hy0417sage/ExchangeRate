package com.hy0417sage.wirebarley.presentation.view

import androidx.lifecycle.*
import com.hy0417sage.wirebarley.data.model.CacheEntity
import com.hy0417sage.wirebarley.domain.CacheRepository
import com.hy0417sage.wirebarley.domain.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    private val cacheRepository: CacheRepository,
) :
    ViewModel() {
    val selectedCurrency = MutableLiveData<Int>()
    init {
        exchangeRepository.setApiKey("Ljk6bSeswHTEVI7HF7rfVDve6tEFxoir")
        updateCache()
    }

    fun getCache() = cacheRepository.getCache()


    private fun updateCache() {
        exchangeRepository.exchangeData().onEach {
            cacheRepository.updateCache(
                CacheEntity(
                    KRW = String.format("%.2f", it.KRW).toDouble(),
                    JPY = String.format("%.2f", it.JPY).toDouble(),
                    PHP = String.format("%.2f", it.PHP).toDouble()
                )
            )
        }.launchIn(viewModelScope)
    }
}
package com.hy0417sage.wirebarley

import androidx.lifecycle.*
import com.hy0417sage.wirebarley.cache.CacheEntity
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

//    fun getCache() : Pair<Double, String>{
//        var data: Pair<Double, String> = Pair(0.0, "")
//        cacheRepository.getCache().onEach { cache ->
//            data = when (selectedCurrency.value) {
//                0 -> Pair(cache.KRW, "KRW")
//                1 -> Pair(cache.JPY, "JPY")
//                else -> Pair(cache.PHP, "PHP")
//            }
//        }.launchIn(viewModelScope)
//        return data
//    }

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
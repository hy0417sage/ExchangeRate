package com.hy0417sage.wirebarley.data

import android.util.Log
import com.hy0417sage.wirebarley.data.model.Exchange
import com.hy0417sage.wirebarley.domain.ExchangeRepository
import com.hy0417sage.wirebarley.data.api.ExchangeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(private val retrofit: Retrofit) :
    ExchangeRepository {

    private val _exchangeData: MutableStateFlow<Exchange.Quotes> = MutableStateFlow(Exchange.Quotes(
        0.0,
        0.0,
        0.0))
    val exchangeData: StateFlow<Exchange.Quotes> = _exchangeData

    private val gitHubService by lazy {
        retrofit.create(ExchangeService::class.java)
    }

    override fun exchangeData(): StateFlow<Exchange.Quotes> {
        return exchangeData
    }

    override fun setApiKey(apiKey: String) {
        return gitHubService.getGitHubService(apiKey).enqueue(object : Callback<Exchange> {
            override fun onResponse(
                call: Call<Exchange>,
                response: Response<Exchange>,
            ) {
                if (response.isSuccessful) {
                    _exchangeData.value = response.body()!!.quotes
                }
            }

            override fun onFailure(call: Call<Exchange>, t: Throwable) {
                Log.d(this@ExchangeRepositoryImpl.toString(), "call failed")
            }
        })
    }
}
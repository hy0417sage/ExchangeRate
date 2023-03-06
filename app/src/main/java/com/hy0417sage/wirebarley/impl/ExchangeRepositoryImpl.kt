package com.hy0417sage.wirebarley.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hy0417sage.wirebarley.Exchange
import com.hy0417sage.wirebarley.ExchangeService
import com.hy0417sage.wirebarley.ExchangeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(private val retrofit: Retrofit) :
    ExchangeRepository {
    val exchangeData: MutableLiveData<Exchange.Quotes> = MutableLiveData()

    private val gitHubService by lazy {
        retrofit.create(ExchangeService::class.java)
    }

    override fun exchangeData(): LiveData<Exchange.Quotes> {
        return exchangeData
    }

    override fun setApiKey(apiKey: String) {
        return gitHubService.getGitHubService(apiKey).enqueue(object : Callback<Exchange> {
            override fun onResponse(
                call: Call<Exchange>,
                response: Response<Exchange>,
            ) {
                if (response.isSuccessful) {
                    exchangeData.value = response.body()?.quotes
                }
            }

            override fun onFailure(call: Call<Exchange>, t: Throwable) {
                Log.d(this@ExchangeRepositoryImpl.toString(), "call failed")
            }
        })
    }
}
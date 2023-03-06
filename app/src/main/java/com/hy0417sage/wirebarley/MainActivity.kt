package com.hy0417sage.wirebarley

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getExchangeRate().observe(this, Observer { exchange ->
            Log.d(this.toString(), "환율 계산 : ${exchange.KRW}")
            binding.exchangeRate.text = exchange.KRW.toString()
        })
    }
}
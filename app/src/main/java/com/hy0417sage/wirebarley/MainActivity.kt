package com.hy0417sage.wirebarley

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hy0417sage.wirebarley.cache.CacheEntity
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var cacheEntity: CacheEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getExchangeRate().observe(this, Observer { exchange ->
            mainViewModel.insertCache(CacheEntity(1, exchange.KRW, exchange.JPY, exchange.PHP))
        })

        mainViewModel.getCache().observe(this, Observer { cache ->
            if (cache != null){
                binding.exchangeRate.text = cache.JPY.toString()
            }
        })
    }
}
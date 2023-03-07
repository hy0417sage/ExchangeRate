package com.hy0417sage.wirebarley.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import com.hy0417sage.wirebarley.R
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
import com.hy0417sage.wirebarley.presentation.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.quotes.observe(this) { exchange ->
            binding.exchangeRate.text = exchange
        }
    }
}


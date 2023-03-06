package com.hy0417sage.wirebarley

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.hy0417sage.wirebarley.cache.CacheEntity
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

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
        alertDialog()

        binding.time.text = DateUtil.dateAndTime()
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setItems(R.array.nation,
            DialogInterface.OnClickListener { dialog, which ->

            }).create().show()
    }
}
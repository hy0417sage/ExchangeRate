package com.hy0417sage.wirebarley.presentation.view

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.hy0417sage.wirebarley.R
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
import com.hy0417sage.wirebarley.presentation.config.BaseActivity
import com.hy0417sage.wirebarley.presentation.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.button.setOnClickListener {
            alertDialog()
        }
        binding.time.text = DateUtil.dateAndTime()
        mainViewModel.getCache().onEach { cache ->
            when(mainViewModel.selectedCurrency.value){
                0 -> Pair(cache?.KRW ?: 0.0, "KRW")
                1 -> Pair(cache?.JPY ?: 0.0, "JPY")
                2 -> Pair(cache?.PHP ?: 0.0, "PHP")
            }
            binding.exchangeRate.text = cache?.KRW.toString()
        }

    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setItems(R.array.nation,
            DialogInterface.OnClickListener { dialog, which ->
                mainViewModel.selectedCurrency.value = which
            }).create().show()
    }
}


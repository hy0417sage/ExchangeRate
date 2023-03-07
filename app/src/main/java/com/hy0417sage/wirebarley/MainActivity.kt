package com.hy0417sage.wirebarley

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
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

//    fun getCache() {
//        var data: Pair<Double, String> = Pair(0.0, "")
//        lifecycleScope.launch {
//            mainViewModel.getCache().onEach { cache ->
//                data = when (mainViewModel.selectedCurrency.value) {
//                    0 -> Pair(cache.KRW, "KRW")
//                    1 -> Pair(cache.JPY, "JPY")
//                    else -> Pair(cache.PHP, "PHP")
//                }
//            }
//        }
//        binding.exchangeRate.text = data.second
//    }


    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setItems(R.array.nation,
            DialogInterface.OnClickListener { dialog, which ->
                mainViewModel.selectedCurrency.value = which
            }).create().show()
    }
}


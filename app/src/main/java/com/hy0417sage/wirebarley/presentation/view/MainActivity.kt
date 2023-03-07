package com.hy0417sage.wirebarley.presentation.view

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.hy0417sage.wirebarley.R
import com.hy0417sage.wirebarley.data.model.Exchange
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
import com.hy0417sage.wirebarley.presentation.config.BaseActivity
import com.hy0417sage.wirebarley.presentation.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var exchangeRate: Exchange.Quotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.quotes.observe(this) { exchange ->
            val df = DecimalFormat("#,###.00")
            exchangeRate = exchange
            binding.exchangeRate.text =  df.format(exchange.KRW) + " KRW/USD"
//            binding.progressBar.isVisible = false
        }
        binding.want.addTextChangedListener(textWatcher)
        binding.button.setOnClickListener {
            alertDialog()
        }
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setItems(R.array.nation,
            DialogInterface.OnClickListener { dialog, which ->
            }).create().show()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(s != null && s.toString() != ""){
                val df = DecimalFormat("#,###.00")
                binding.total.text = df.format(s.toString().toDouble() * exchangeRate.KRW)
                binding.time.text = DateUtil.dateAndTime()
//                수취금액은 113,004.98 KRW 입니다.
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
}


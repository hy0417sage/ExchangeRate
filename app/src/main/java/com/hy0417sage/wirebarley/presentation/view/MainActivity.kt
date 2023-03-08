package com.hy0417sage.wirebarley.presentation.view

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.hy0417sage.wirebarley.R
import com.hy0417sage.wirebarley.databinding.ActivityMainBinding
import com.hy0417sage.wirebarley.presentation.config.BaseActivity
import com.hy0417sage.wirebarley.presentation.util.DateUtil
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private var exchangeRate: List<Double> = listOf(0.0, 0.0, 0.0)
    private var now: Int = 0
    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.quotes.observe(this) { exchangeRate ->
            this.exchangeRate = exchangeRate
            changeView()
        }
        binding.want.addTextChangedListener(textWatcher)
        mainViewModel.now.observe(this) { nation ->
            this.now = nation
            changeView()
        }
        binding.button.setOnClickListener {
            alertDialog()
        }
    }

    private fun changeView() {
        val df = DecimalFormat("#,###.00")
        val nation = arrayOf("KRW", "JPY", "PHP")
        binding.exchangeRate.text = df.format(exchangeRate[now]) + " ${nation[now]}/USD"
        binding.total.text =
            "수취금액은 ${
                df.format(total * exchangeRate[now])
            } ${nation[now]}입니다."
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        val nation = arrayOf("한국(KRW)", "일본(JPY)", "필리핀(PHP)")
        builder.setItems(nation, DialogInterface.OnClickListener { dialog, which ->
            mainViewModel.changeExchangeRate(which)
            binding.send.text = nation[which]
        }).create().show()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s != null && s.toString() != "") {
                val df = DecimalFormat("#,###.00")
                val nation = arrayOf("KRW", "JPY", "PHP")
                total = s.toString().toDouble()
                binding.total.text =
                    "수취금액은 ${
                        df.format(total * exchangeRate[now])
                    } ${nation[now]}입니다."
                binding.time.text = DateUtil.dateAndTime()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
}


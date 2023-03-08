package com.hy0417sage.wirebarley.presentation.view

import android.content.DialogInterface
import android.graphics.Color
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val df = DecimalFormat("#,###.00")
        val nation = arrayOf("KRW", "JPY", "PHP")
        val exchangeRate = "%s %s/USD"
        val total = "수취금액은 %s %s 입니다"

        mainViewModel.viewData.observe(this) { viewData ->
            binding.exchangeRate.text = if (viewData.exchangeRate == 0.0) {
                "네트워크 연결에 실패했습니다."
            } else {
                exchangeRate.format(df.format(viewData.exchangeRate), nation[viewData.nation])
            }
            binding.receivedAmount.text = if (viewData.total == 0.0) {
                "송금액을 입력해 주세요."
            } else {
                total.format(df.format(viewData.total), nation[viewData.nation])
            }
        }
        binding.remittanceAmount.addTextChangedListener(textWatcher)
        binding.button.setOnClickListener {
            alertDialog()
        }
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        val nation = arrayOf("한국(KRW)", "일본(JPY)", "필리핀(PHP)")
        builder.setItems(nation, DialogInterface.OnClickListener { dialog, index ->
            binding.recipientCountry.text = nation[index]
            binding.remittanceAmount.text = null
            mainViewModel.exchangeRate(index, null)
        }).create().show()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            binding.receivedAmount.setTextColor(Color.BLACK)
            if (s != null && s.toString().isNotEmpty()) {
                if (s.toString().toInt() <= 10000) {
                    mainViewModel.totalExchange(s.toString())
                    binding.lookupTime.text = DateUtil.dateAndTime()
                } else {
                    binding.receivedAmount.text = "송금액이 바르지 않습니다."
                    binding.receivedAmount.setTextColor(Color.RED)
                }
            } else {
                mainViewModel.totalExchange(null)
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
}


package com.bitpanda.developertest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bitpanda.developertest.R
import com.bitpanda.developertest.databinding.ActivityDetailBinding
import com.bitpanda.developertest.ext.getCurrencyFormatValue
import com.bitpanda.developertest.model.Cryptocoin
import com.bitpanda.developertest.model.Fiat
import com.bitpanda.developertest.model.Metal
import com.bitpanda.developertest.remote.DummyWebService

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val walletListItem = DummyWebService().getCurrencies().filter {
            it.walletItemId == intent.extras?.getString("walletDataId") &&
                    when (intent.extras?.getString("walletDataType")) {
                        "FIAT" -> it.walletItem is Fiat
                        "CRYPTO" -> it.walletItem is Cryptocoin
                        "METAL" -> it.walletItem is Metal
                        else -> it.walletItem is Fiat
                    }
        }
        binding.name.text = walletListItem.firstOrNull()?.name
        binding.balance.text = walletListItem.firstOrNull()?.balance?.getCurrencyFormatValue()
        binding.name.text = walletListItem.firstOrNull()?.name
        binding.name.text = walletListItem.firstOrNull()?.name

    }


}
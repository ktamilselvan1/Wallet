package com.bitpanda.developertest.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.developertest.R
import com.bitpanda.developertest.model.Cryptocoin
import com.bitpanda.developertest.model.Fiat
import com.bitpanda.developertest.model.Metal
import com.bitpanda.developertest.remote.DummyWebService
import com.bitpanda.developertest.repository.WalletData
import com.bitpanda.developertest.ui.adapter.WalletListAdapter


class MainActivity : AppCompatActivity() {


    private lateinit var adapter: WalletListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var walletListItems: List<WalletData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.walletList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = WalletListAdapter { walletItem ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("walletDataId", walletItem.id)
            val type = when (walletItem.walletItem) {
                is Fiat -> "FIAT"
                is Cryptocoin -> "CRYPTO"
                is Metal -> "METAL"
                else -> "FIAT"
            }
            intent.putExtra("walletDataType", type)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        walletListItems = DummyWebService().getCurrencies()
        showItems(0)
    }

    fun sortWalletList(wallets: List<WalletData>) {

    }

    private fun showWalletList(wallets: List<WalletData>) {
        adapter.submitList(wallets)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_all -> {
                item.isChecked = !item.isChecked
                showItems(0)
                true
            }
            R.id.menu_fiat -> {
                item.isChecked = !item.isChecked
                showItems(1)
                true
            }
            R.id.menu_crypto_coin -> {
                item.isChecked = !item.isChecked
                showItems(2)
                true
            }
            R.id.menu_metal -> {
                item.isChecked = !item.isChecked
                showItems(3)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showItems(i: Int) {
        val wallets = arrayListOf<WalletData>()
        when (i) {
            0 -> {
                wallets.addAll(walletListItems)
            }
            1 -> {
                wallets.addAll(walletListItems.filter { it.walletItem is Fiat })
            }
            2 -> {
                wallets.addAll(walletListItems.filter { it.walletItem is Cryptocoin })
            }
            3 -> {
                wallets.addAll(walletListItems.filter { it.walletItem is Metal })
            }
        }
        showWalletList(wallets)
    }


}
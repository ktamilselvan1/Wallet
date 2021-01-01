package com.bitpanda.developertest.remote

import com.bitpanda.developertest.model.*
import com.bitpanda.developertest.remote.DummyData.Companion.cryptocoins
import com.bitpanda.developertest.remote.DummyData.Companion.dummyCryptocoinWalletList
import com.bitpanda.developertest.remote.DummyData.Companion.dummyEURWallet
import com.bitpanda.developertest.remote.DummyData.Companion.dummyMetalWalletList
import com.bitpanda.developertest.remote.DummyData.Companion.fiats
import com.bitpanda.developertest.remote.DummyData.Companion.metals
import com.bitpanda.developertest.repository.WalletData

class DummyWebService {

    private fun getCryptoWallets(): List<CryptocoinWallet> {
        return dummyCryptocoinWalletList.sortedByDescending {
            it.balance
        }
    }

    private fun getMetalWallets(): List<MetalWallet> {
        return dummyMetalWalletList.sortedByDescending {
            it.balance
        }
    }

    private fun getFiatWallets(): List<FiatWallet> {
        return dummyEURWallet.sortedByDescending {
            it.balance
        }
    }

    private fun getCryptocoins(): List<Cryptocoin> {
        return cryptocoins
    }

    private fun getMetals(): List<Metal> {
        return metals
    }

    private fun getFiats(): List<Fiat> {
        return fiats
    }

    fun getCurrencies(): List<WalletData> {
        val walletListData = arrayListOf<WalletData>()
        val fiatMap: Map<String, Fiat> = getFiats().map { it.id to it }.toMap()
        val cryptoMap: Map<String, Cryptocoin> = getCryptocoins().map { it.id to it }.toMap()
        val metalMap: Map<String, Metal> = getMetals().map { it.id to it }.toMap()
        getFiatWallets()
            .map { fiatWallet ->
                val walletData =
                    WalletData(
                        fiatWallet.id,
                        fiatWallet.name,
                        fiatWallet.balance,
                        fiatWallet.fiatId,
                        fiatMap[fiatWallet.fiatId]
                    )
                walletListData.add(walletData)
            }
        getCryptoWallets()
            .map { cryptocoinWallet ->
                val walletData =
                    WalletData(
                        cryptocoinWallet.id,
                        cryptocoinWallet.name,
                        cryptocoinWallet.balance,
                        cryptocoinWallet.cryptocoinId,
                        cryptoMap[cryptocoinWallet.cryptocoinId]
                    )
                walletListData.add(walletData)
            }
        getMetalWallets()
            .map { metalWallet ->
                val walletData =
                    WalletData(
                        metalWallet.id,
                        metalMap[metalWallet.metalId]?.name ?: metalWallet.name,
                        metalWallet.balance,
                        metalWallet.metalId,
                        metalMap[metalWallet.metalId]
                    )
                walletListData.add(walletData)
            }

        return walletListData
    }
}
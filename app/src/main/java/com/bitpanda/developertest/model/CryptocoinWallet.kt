package com.bitpanda.developertest.model

class CryptocoinWallet(var id: String = "",
                       var cryptocoinId: String = "",
                       var isDefault: Boolean = false,
                       var balance: Double = 0.0,
                       var deleted: Boolean = false,
                       var name: String = ""
) {



    //todo implement me
    fun reduceBalance(amount: Double) {
    }

    //todo implement me
    fun addBalance(amount: Double) {
    }



}
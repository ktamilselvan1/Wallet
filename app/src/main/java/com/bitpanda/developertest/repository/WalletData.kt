package com.bitpanda.developertest.repository


class WalletData(
    var id: String,
    var name: String,
    var balance: Double,
    var walletItemId: String,
    var walletItem: Any?
)
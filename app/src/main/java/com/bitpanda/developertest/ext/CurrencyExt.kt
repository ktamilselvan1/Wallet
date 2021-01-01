package com.bitpanda.developertest.ext

import java.text.NumberFormat
import java.util.*

fun Double.getCurrencyFormatValue(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("EUR")
    return format.format(this)
}
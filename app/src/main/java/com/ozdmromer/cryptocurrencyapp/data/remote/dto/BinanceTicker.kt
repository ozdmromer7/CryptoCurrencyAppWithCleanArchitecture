package com.ozdmromer.cryptocurrencyapp.data.remote.dto

data class BinanceTicker(
    val e: String,   // Event type
    val E: Long,     // Event time
    val P: String,     // Event time
    val s: String,   // Symbol
    val c: String    // Last price
)

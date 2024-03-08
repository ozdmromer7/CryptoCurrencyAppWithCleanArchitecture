package com.ozdmromer.cryptocurrencyapp.presentation.coin_price

import com.ozdmromer.cryptocurrencyapp.data.remote.CryptoModel

data class CoinPriceState(
    val isLoading : Boolean = false,
    var data : CryptoModel? = null,
    val error : String = ""
)

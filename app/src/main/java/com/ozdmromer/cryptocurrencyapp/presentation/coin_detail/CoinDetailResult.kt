package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail

import com.ozdmromer.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailResult(
    val isLoading: Boolean = false,
    val error: String = "",
    val coinDetail: CoinDetail? = null
)
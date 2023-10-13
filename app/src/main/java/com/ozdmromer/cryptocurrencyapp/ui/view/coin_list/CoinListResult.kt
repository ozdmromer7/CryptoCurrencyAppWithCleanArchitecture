package com.ozdmromer.cryptocurrencyapp.ui.view.coin_list

import com.ozdmromer.cryptocurrencyapp.domain.model.Coin

data class CoinListResult(
    val isLoading: Boolean = false,
    val allCoins: List<Coin> = emptyList(),
    val error: String = "",
)
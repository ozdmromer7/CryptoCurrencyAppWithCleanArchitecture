package com.ozdmromer.cryptocurrencyapp.presentation

sealed class Screen(val route: String) {
    object CoinDetailScreen : Screen(route = "coin_detail_screen")
    object CoinListScreen : Screen(route = "coin_list_screen")

}
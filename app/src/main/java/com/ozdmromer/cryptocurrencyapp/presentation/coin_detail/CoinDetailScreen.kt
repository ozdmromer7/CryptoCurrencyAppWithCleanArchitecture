package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel= hiltViewModel(),
) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            state.coinDetail?.let { Text(text = it.name) }
            state.coinDetail?.let { Text(text = it.description) }
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}
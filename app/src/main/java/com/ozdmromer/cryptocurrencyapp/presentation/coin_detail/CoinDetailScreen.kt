package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozdmromer.cryptocurrencyapp.data.remote.CryptoModel
import com.ozdmromer.cryptocurrencyapp.presentation.coin_detail.components.CoinDetailComponent
import com.ozdmromer.cryptocurrencyapp.presentation.coin_price.CoinPriceViewModel


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel(),
    coinPriceViewModel: CoinPriceViewModel = hiltViewModel()

    ) {

    val state = viewModel.state.value
    val state2 = coinPriceViewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        state.coinDetail?.let {
                CoinDetailComponent(
                    onClick = {
                        navController.popBackStack()
                    }, coinDetail = it,
                    cryptoModel = state2.data ?: CryptoModel("0.0","")
                )

        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}

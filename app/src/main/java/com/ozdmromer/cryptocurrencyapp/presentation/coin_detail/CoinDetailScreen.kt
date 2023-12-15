package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ozdmromer.cryptocurrencyapp.presentation.MainActivity
import com.ozdmromer.cryptocurrencyapp.presentation.coin_detail.components.CoinDetailComponent


@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel(),

    ) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        state.coinDetail?.let {
            CoinDetailComponent(
                onClick = {
                    navController.popBackStack()
                }, coinDetail = it
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

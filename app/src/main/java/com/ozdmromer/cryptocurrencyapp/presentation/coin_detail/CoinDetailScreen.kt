package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel(),

) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Default.KeyboardArrowLeft,"backButton")
            }
            state.coinDetail?.let { it.name?.let { it1 -> Text(text = it1) } }
            state.coinDetail?.let { it.description?.let { it1 -> Text(text = it1) } }
            state.coinDetail?.let { it.rank?.let { it1 -> Text(text = it1.toString()) } }
            state.coinDetail?.let { it.tags?.let { it1 -> Text(text = it1.toString()) } }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}
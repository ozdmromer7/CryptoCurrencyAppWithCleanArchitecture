package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ozdmromer.cryptocurrencyapp.R

@Composable
fun PriceChangeComponent(
    priceChangePercent: String?
) {

    return Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        if (!priceChangePercent.isNullOrEmpty()) {
            if ((priceChangePercent.toDouble()) > 0.0) {
                Image(
                    painterResource(id = R.drawable.upload),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
            } else {
                Image(
                    painterResource(id = R.drawable.down_arrow),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
            }
            Text(
                text = priceChangePercent, color = Color.White
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.width(22.dp))
        }


    }
}
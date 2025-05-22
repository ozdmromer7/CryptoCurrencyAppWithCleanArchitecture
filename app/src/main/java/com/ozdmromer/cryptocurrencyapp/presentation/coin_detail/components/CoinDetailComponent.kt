package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozdmromer.cryptocurrencyapp.data.remote.CryptoModel
import com.ozdmromer.cryptocurrencyapp.domain.model.CoinDetail

@Composable
fun CoinDetailComponent(
    onClick: () -> Unit,
    coinDetail: CoinDetail,
    cryptoModel: CryptoModel
) {
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        IconButton(onClick = onClick) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                "backButton",
                Modifier.size(100.dp),
                tint = Color.White
            )
        }
        Row(
            Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            coinDetail.name?.let {
                Text(
                    text = it,
                    style = TextStyle(color = Color.White),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = if (cryptoModel.price == "0.0") "Price was not found" else cryptoModel.price,
                style = TextStyle(color = Color.White),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            PriceChangeComponent(
                cryptoModel.priceChange
            )
        }
        coinDetail.description?.let {
            Text(
                text = "\t" + it,
                style = TextStyle(
                    color = Color.White
                )
            )
        }
        LazyColumn {
            item {
                Text(
                    text = "Team Members", fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
            }
            items(coinDetail.team ?: arrayListOf()) { teamMember ->

                Text(
                    text = "\t" + teamMember.name,
                    style = TextStyle(color = Color.White)
                )
                Text(
                    text = "\t" + teamMember.position,
                    style = TextStyle(color = Color.White)
                )

            }
            // Tags
            item {
                Text(
                    text = "Tags", fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
            }
            items(coinDetail.tags ?: arrayListOf()) { tags ->
                Text(
                    text = "\t" + tags,
                    style = TextStyle(color = Color.White)
                )

            }
        }
    }
}

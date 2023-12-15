package com.ozdmromer.cryptocurrencyapp.presentation.coin_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.ozdmromer.cryptocurrencyapp.domain.model.Coin

@Composable
fun CardListComponent(
    coin:Coin,
    onClick: () -> Unit
){
    OutlinedCard(Modifier.fillMaxWidth().
        height(60.dp).
    clickable(onClick=onClick),
        border = BorderStroke(0.dp, Color.Black)
    ) {
        Row(Modifier
                .fillMaxSize()
            .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(Modifier.padding(15.dp)) {
                Row {
                    Text(text ="${coin.rank}. ",
                        style = TextStyle(
                            color = Color.White,
                        ))
                    Text(text = coin.name +"\t",
                        style = TextStyle(
                            color = Color.White
                        ))
                    Text(text = "(${coin.symbol})",
                        style = TextStyle(
                            color = Color.White
                        ))
                }
            }
            Text(
                modifier = Modifier.padding(end = 15.dp),
                text = if (coin.isActive) "active" else "not active",
                style = TextStyle(
                    color = Color.Green,
                    fontStyle = FontStyle.Italic

                )
            )
        }
    }
    
}
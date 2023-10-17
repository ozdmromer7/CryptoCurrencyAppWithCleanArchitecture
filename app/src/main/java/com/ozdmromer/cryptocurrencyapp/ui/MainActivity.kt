package com.ozdmromer.cryptocurrencyapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ozdmromer.cryptocurrencyapp.ui.theme.CryptoCurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrencyAppTheme {

                FirstScreen()
            }
        }
    }
}
@Composable
fun FirstScreen() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CryptoCurrencyAppTheme {
        FirstScreen()
    }
}
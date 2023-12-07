package com.ozdmromer.cryptocurrencyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozdmromer.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.ozdmromer.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import com.ozdmromer.cryptocurrencyapp.presentation.ui.theme.CryptoCurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CryptoCurrencyAppTheme {

               val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.CoinListScreen.route ){
                    composable(route = Screen.CoinListScreen.route){
                        CoinListScreen(navController)
                    }
                    composable(route = Screen.CoinDetailScreen.route + "/{coinId}"){
                        CoinDetailScreen(navController)
                    }
                }
            }
        }
    }
}


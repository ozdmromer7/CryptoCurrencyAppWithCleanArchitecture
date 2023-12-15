package com.ozdmromer.cryptocurrencyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozdmromer.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.ozdmromer.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import com.ozdmromer.cryptocurrencyapp.presentation.ui.theme.CryptoCurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint


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


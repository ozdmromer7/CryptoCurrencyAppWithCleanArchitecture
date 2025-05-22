package com.ozdmromer.cryptocurrencyapp.presentation.coin_price

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozdmromer.cryptocurrencyapp.common.Constant.PARAM_COIN_SYMBOL
import com.ozdmromer.cryptocurrencyapp.common.Resource
import com.ozdmromer.cryptocurrencyapp.data.remote.CryptoModel
import com.ozdmromer.cryptocurrencyapp.domain.use_case.get_coin_price.GetCoinPriceUseCase
import com.ozdmromer.cryptocurrencyapp.presentation.coin_price_web_socket.BinanceWebSocketClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale
import javax.inject.Inject


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinPriceViewModel @Inject constructor(
    private val coinPriceUseCase: GetCoinPriceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val webSocketClient = BinanceWebSocketClient()
    private val _state = mutableStateOf(CoinPriceState())
    var state: State<CoinPriceState> = _state


    init {
        savedStateHandle.get<String>(PARAM_COIN_SYMBOL)?.let {
            getCoinPrice(it + "USDT")
            connectToSocket((it.lowercase(Locale.ROOT) + "usdt"))
        }
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getCoinPrice(symbol: String) {

        coinPriceUseCase(symbol).onEach {
            when (it) {
                is Resource.Success -> _state.value =
                    CoinPriceState(data = it.data, isLoading = false)

                is Resource.Error -> _state.value =
                    CoinPriceState(error = it.message ?: "", isLoading = false)

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("DefaultLocale")
    private fun connectToSocket(coinParameter: String) {
        webSocketClient.connect(coinParameter) { ticker ->
            val formatted = String.format("%.2f", ticker.c.toDoubleOrNull())
            val formattedChange = String.format("%.2f", ticker.P.toDoubleOrNull())

            _state.value = _state.value.copy(
                data = CryptoModel(price = formatted, priceChange = formattedChange)
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        webSocketClient.disconnect()
    }
}
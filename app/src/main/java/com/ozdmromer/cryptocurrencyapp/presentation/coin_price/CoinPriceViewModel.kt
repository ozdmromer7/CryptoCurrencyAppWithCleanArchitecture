package com.ozdmromer.cryptocurrencyapp.presentation.coin_price

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozdmromer.cryptocurrencyapp.common.Constant.PARAM_COIN_SYMBOL
import com.ozdmromer.cryptocurrencyapp.common.Resource
import com.ozdmromer.cryptocurrencyapp.domain.use_case.get_coin_price.GetCoinPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinPriceViewModel @Inject constructor(
    private val coinPriceUseCase: GetCoinPriceUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    init {
        savedStateHandle.get<String>(PARAM_COIN_SYMBOL)?.let {
            getCoinPrice(it+"USDT")
        }
    }


    private val _state = mutableStateOf(CoinPriceState())
        var state : State<CoinPriceState> = _state
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getCoinPrice(symbol:String){

         coinPriceUseCase(symbol).onEach {
             when(it){
                 is Resource.Success -> _state.value = CoinPriceState(data = it.data, isLoading = false)
                 is Resource.Error -> _state.value = CoinPriceState(error = it.message?: "" , isLoading = false)
                 else -> {}
             }
         }.launchIn(viewModelScope)
    }
}
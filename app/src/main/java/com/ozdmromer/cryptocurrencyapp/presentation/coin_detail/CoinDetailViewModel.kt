package com.ozdmromer.cryptocurrencyapp.presentation.coin_detail

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozdmromer.cryptocurrencyapp.R
import com.ozdmromer.cryptocurrencyapp.common.Constant.PARAM_COIN_ID
import com.ozdmromer.cryptocurrencyapp.common.Resource
import com.ozdmromer.cryptocurrencyapp.domain.use_case.get_coin_detail.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailResult())
    val state: State<CoinDetailResult> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->

            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {

        coinDetailUseCase(coinId).onEach { result ->

            when (result) {
                is Resource.Loading -> {

                    _state.value = CoinDetailResult(isLoading = true)

                }

                is Resource.Error -> {

                    _state.value = CoinDetailResult(
                        error = result.message ?: Resources.getSystem().getString(
                            R.string.unexpected_error
                        ), isLoading = false
                    )

                }

                is Resource.Success -> {

                    _state.value = CoinDetailResult(coinDetail = result.data, isLoading = false)

                }
            }
        }.launchIn(viewModelScope)


    }
}
package com.ozdmromer.cryptocurrencyapp.presentation.coin_list

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozdmromer.cryptocurrencyapp.R
import com.ozdmromer.cryptocurrencyapp.common.Resource
import com.ozdmromer.cryptocurrencyapp.domain.use_case.get_all_coins.GetAllCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getAllCoinsUseCase: GetAllCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListResult())
    val state: State<CoinListResult> = _state
    private var job: Job? = null

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        job?.cancel()
        job = getAllCoinsUseCase().onEach { result ->

            when (result) {
                is Resource.Loading -> {

                    _state.value = CoinListResult(isLoading = true)
                }

                is Resource.Error -> {

                    _state.value = CoinListResult(
                        error = result.message ?: Resources.getSystem().getString(
                            R.string.unexpected_error
                        )
                    )

                }

                is Resource.Success -> {

                    _state.value = CoinListResult(allCoins = result.data ?: emptyList())

                }
            }

        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
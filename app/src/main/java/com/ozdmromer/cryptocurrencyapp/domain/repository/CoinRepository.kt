package com.ozdmromer.cryptocurrencyapp.domain.repository

import com.ozdmromer.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.ozdmromer.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getAllCoins(): List<CoinDto>

    suspend fun getCoinDetails(coinId: String) : CoinDetailDto
}
package com.ozdmromer.cryptocurrencyapp.data.repository

import com.ozdmromer.cryptocurrencyapp.data.remote.PaprikaCoinApi
import com.ozdmromer.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.ozdmromer.cryptocurrencyapp.data.remote.dto.CoinDto
import com.ozdmromer.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: PaprikaCoinApi
) : CoinRepository {
    override suspend fun getAllCoins(): List<CoinDto> {

        return api.getAllCoins()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailDto {

        return api.getCoinDetails(coinId)
    }

}
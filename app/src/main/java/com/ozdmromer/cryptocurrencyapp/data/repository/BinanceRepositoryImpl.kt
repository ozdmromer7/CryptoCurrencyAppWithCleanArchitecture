package com.ozdmromer.cryptocurrencyapp.data.repository

import com.ozdmromer.cryptocurrencyapp.data.remote.BinanceApi
import com.ozdmromer.cryptocurrencyapp.data.remote.CryptoModel
import com.ozdmromer.cryptocurrencyapp.domain.repository.BinanceRepository
import javax.inject.Inject

class BinanceRepositoryImpl @Inject constructor(
    private val api: BinanceApi) : BinanceRepository {
    override suspend fun getCryptoPriceAndSymbol(symbol: String): CryptoModel? {

         if(api.getPriceFromSymbol(symbol).isSuccessful){
           return api.getPriceFromSymbol(symbol).body()
        }
        return null
    }
}
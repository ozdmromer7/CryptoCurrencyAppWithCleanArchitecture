package com.ozdmromer.cryptocurrencyapp.domain.repository

import com.ozdmromer.cryptocurrencyapp.data.remote.CryptoModel

interface BinanceRepository {
    suspend fun getCryptoPriceAndSymbol(symbol:String) : CryptoModel?
}
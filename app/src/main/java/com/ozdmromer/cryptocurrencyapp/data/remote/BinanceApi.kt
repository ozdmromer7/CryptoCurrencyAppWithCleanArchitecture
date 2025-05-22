package com.ozdmromer.cryptocurrencyapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceApi {
    @GET("api/v3/ticker/price")
    suspend fun getPriceFromSymbol(
        @Query("symbol") symbol : String): Response<CryptoModel>
}
data class CryptoModel(
    val price : String,
    val priceChange : String
)
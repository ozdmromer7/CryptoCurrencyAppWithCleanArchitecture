package com.ozdmromer.cryptocurrencyapp.data.remote

import com.ozdmromer.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.ozdmromer.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PaprikaCoinApi {

    @GET("/v1/coins")
    suspend fun getAllCoins() : List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String) : CoinDetailDto

}

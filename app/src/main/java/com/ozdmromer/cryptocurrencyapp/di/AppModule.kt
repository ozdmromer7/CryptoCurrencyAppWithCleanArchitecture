package com.ozdmromer.cryptocurrencyapp.di

import com.ozdmromer.cryptocurrencyapp.common.Constant.BASE_URL
import com.ozdmromer.cryptocurrencyapp.common.Constant.BINANCE_BASE_URL
import com.ozdmromer.cryptocurrencyapp.common.TokenInterceptor
import com.ozdmromer.cryptocurrencyapp.data.remote.BinanceApi
import com.ozdmromer.cryptocurrencyapp.data.remote.PaprikaCoinApi
import com.ozdmromer.cryptocurrencyapp.data.repository.BinanceRepositoryImpl
import com.ozdmromer.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.ozdmromer.cryptocurrencyapp.domain.repository.BinanceRepository
import com.ozdmromer.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaCoinApi(): PaprikaCoinApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(TokenInterceptor.httpClient)
            .build()
            .create(PaprikaCoinApi::class.java)

    }

    @Provides
    @Singleton
    fun provideBinanceApi(): BinanceApi {
        return Retrofit.Builder()
            .baseUrl(BINANCE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(TokenInterceptor.httpClient)
            .build()
            .create(BinanceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBinanceRepository(api : BinanceApi) : BinanceRepository {

        return BinanceRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: PaprikaCoinApi): CoinRepository {

        return CoinRepositoryImpl(api = api)
    }
}
package com.ozdmromer.cryptocurrencyapp.di

import com.ozdmromer.cryptocurrencyapp.common.Constant.BASE_URL
import com.ozdmromer.cryptocurrencyapp.data.remote.PaprikaCoinApi
import com.ozdmromer.cryptocurrencyapp.data.repository.CoinRepositoryImpl
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
            .build()
            .create(PaprikaCoinApi::class.java)

    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: PaprikaCoinApi): CoinRepository {

        return CoinRepositoryImpl(api = api)
    }
}
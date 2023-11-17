package com.ozdmromer.cryptocurrencyapp.domain.use_case.get_coin_detail

import android.content.res.Resources
import com.ozdmromer.cryptocurrencyapp.R
import com.ozdmromer.cryptocurrencyapp.common.Resource
import com.ozdmromer.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.ozdmromer.cryptocurrencyapp.domain.model.CoinDetail
import com.ozdmromer.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {

        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinDetails(coinId).toCoinDetail()
            emit(Resource.Success(data = coinDetail))
        } catch (e: HttpException) {

            emit(
                Resource.Error(
                    message = e.localizedMessage ?: Resources.getSystem()
                        .getString(R.string.unexpected_error)
                )
            )

        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: Resources.getSystem()
                        .getString(R.string.no_internet_connection)
                )
            )
        }


    }
}
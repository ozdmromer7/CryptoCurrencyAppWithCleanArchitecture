package com.ozdmromer.cryptocurrencyapp.domain.use_case.get_all_coins

import android.content.res.Resources
import com.ozdmromer.cryptocurrencyapp.R
import com.ozdmromer.cryptocurrencyapp.common.Resource
import com.ozdmromer.cryptocurrencyapp.data.remote.dto.toCoin
import com.ozdmromer.cryptocurrencyapp.domain.model.Coin
import com.ozdmromer.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.NullPointerException
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {

        try {
            emit(Resource.Loading())
            val coins = repository.getAllCoins().map { it.toCoin() }
            emit(Resource.Success(data = coins))

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
        catch (e : NullPointerException){
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Null point exception"
                )
            )
        }

    }
}
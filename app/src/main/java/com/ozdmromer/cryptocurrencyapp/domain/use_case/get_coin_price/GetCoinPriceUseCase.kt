package com.ozdmromer.cryptocurrencyapp.domain.use_case.get_coin_price

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.ozdmromer.cryptocurrencyapp.common.*
import com.ozdmromer.cryptocurrencyapp.data.remote.CryptoModel
import com.ozdmromer.cryptocurrencyapp.domain.repository.BinanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.NullPointerException
import javax.inject.Inject

class GetCoinPriceUseCase @Inject constructor(
    private val binanceRepository: BinanceRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(symbol : String ) : Flow<Resource<CryptoModel>>  = flow {

        try {
            emit(Resource.Loading())
            val data = binanceRepository.getCryptoPriceAndSymbol(symbol)
            emit(Resource.Success(data))
        }
        catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
        catch (e : IOException){
            emit(Resource.Error(e.localizedMessage ?: ""))

        }
        catch (e : NoSuchElementException) {
            emit(Resource.Error(e.localizedMessage ?: ""))

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
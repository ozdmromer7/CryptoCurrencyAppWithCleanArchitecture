package com.ozdmromer.cryptocurrencyapp.common

import android.content.res.Resources
import com.ozdmromer.cryptocurrencyapp.R.string
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Resource<out T>(val data: T?=null, val message: String?=null) {

    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String,data:T?= null) : Resource<T>(data,message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

fun <T> Flow<T>.asResult() : Flow<Resource<T>> {

    return this
        .map<T,Resource<T>> {
            Resource.Success(it)
        }
        .onStart {
            emit(Resource.Loading())
        }
        .catch {
            emit(Resource.Error(it.message?: Resources.getSystem().getString(string.unexpected_error))) }
}

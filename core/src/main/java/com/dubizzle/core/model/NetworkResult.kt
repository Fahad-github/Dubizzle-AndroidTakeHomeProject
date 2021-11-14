package com.dubizzle.core.model

sealed class NetworkResult<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}
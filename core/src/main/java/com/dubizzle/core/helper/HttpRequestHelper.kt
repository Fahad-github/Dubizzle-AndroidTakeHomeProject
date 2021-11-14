package com.dubizzle.core.helper

import com.dubizzle.core.model.NetworkResult
import com.dubizzle.core.networkInterface.INetworkApi
import retrofit2.Response
import okhttp3.ResponseBody
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HttpRequestHelper : KoinComponent {

    private val networkApi: INetworkApi by inject()

    suspend fun get(
        url: String,
        params: HashMap<String, String>,
    ): NetworkResult<Response<ResponseBody>> {
        return try {
            val await = networkApi.callGetAsync(url, params).await()
            NetworkResult.Success(await)
        } catch (ex: Exception) {
            val exception =
                java.lang.Exception(ex)
            NetworkResult.Error(exception)
        }
    }
}
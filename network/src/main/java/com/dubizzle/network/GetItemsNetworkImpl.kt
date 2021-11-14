package com.dubizzle.network

import com.dubizzle.core.helper.Constants
import com.dubizzle.core.model.GetItemModel
import com.dubizzle.core.model.NetworkResult
import com.dubizzle.core.networkInterface.IGetItemsNetwork
import com.squareup.moshi.Types

class GetItemsNetworkImpl : BaseNetworkImpl(),IGetItemsNetwork {

    override suspend fun getItems(): NetworkResult<GetItemModel> {
        val parameterizedType =
            Types.newParameterizedType(GetItemModel::class.java, GetItemModel::class.java)

        val response = get(
            baseUrl = Constants.baseUrl,
            endPoint = "",
            params = HashMap(),
            type = GetItemModel(),
            parameterizedType = parameterizedType
        )

        return when (response) {
            is NetworkResult.Success -> {
                NetworkResult.Success(response.data)
            }
            is NetworkResult.Error -> {
                NetworkResult.Error(response.exception)
            }
        }
    }
}
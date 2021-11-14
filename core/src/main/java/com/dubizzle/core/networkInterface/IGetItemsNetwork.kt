package com.dubizzle.core.networkInterface

import com.dubizzle.core.model.GetItemModel
import com.dubizzle.core.model.NetworkResult

interface IGetItemsNetwork {
    suspend fun getItems(): NetworkResult<GetItemModel>
}
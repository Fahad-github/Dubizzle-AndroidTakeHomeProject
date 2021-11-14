package com.dubizzle.core.serviceInterface

import com.dubizzle.core.model.GetItemModel
import com.dubizzle.core.model.NetworkResult

interface IGetItemsService {
    suspend fun getItems(): NetworkResult<GetItemModel>
}
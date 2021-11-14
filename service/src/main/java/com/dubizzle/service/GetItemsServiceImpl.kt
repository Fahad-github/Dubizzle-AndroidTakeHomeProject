package com.dubizzle.service

import com.dubizzle.core.helper.Constants.errorMessage
import com.dubizzle.core.model.GetItemModel
import com.dubizzle.core.model.NetworkResult
import com.dubizzle.core.networkInterface.IGetItemsNetwork
import com.dubizzle.core.serviceInterface.IGetItemsService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetItemsServiceImpl : IGetItemsService,KoinComponent {

    private val searchMusicResultNetwork: IGetItemsNetwork by inject()

    override suspend fun getItems(): NetworkResult<GetItemModel> {
        return when (val activity = searchMusicResultNetwork.getItems()) {
            is NetworkResult.Success -> {
                NetworkResult.Success(activity.data)
            }
            is NetworkResult.Error -> {
                NetworkResult.Error(Exception(errorMessage))
            }
        }
    }
}
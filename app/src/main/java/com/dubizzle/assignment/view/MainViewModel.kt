package com.dubizzle.assignment.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dubizzle.core.model.GetItemModel
import com.dubizzle.core.model.NetworkResult
import com.dubizzle.core.serviceInterface.IGetItemsService
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val getItemsService: IGetItemsService by inject()
    val getItemLiveData: MutableLiveData<NetworkResult<GetItemModel>> = MutableLiveData()

    fun getItems() = viewModelScope.launch {
        when (val response = getItemsService.getItems()) {
            is NetworkResult.Success -> getItemLiveData.postValue(response)
            is NetworkResult.Error -> getItemLiveData.postValue(NetworkResult.Error(response.exception))
        }
    }
}
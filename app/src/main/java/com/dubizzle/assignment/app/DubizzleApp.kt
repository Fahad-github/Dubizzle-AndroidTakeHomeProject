package com.dubizzle.assignment.app

import android.app.Application
import com.dubizzle.core.helper.Constants
import com.dubizzle.core.helper.HttpRequestHelper
import com.dubizzle.core.networkInterface.IBaseNetwork
import com.dubizzle.core.networkInterface.IGetItemsNetwork
import com.dubizzle.core.networkInterface.INetworkApi
import com.dubizzle.core.serviceInterface.IGetItemsService
import com.dubizzle.network.BaseNetworkImpl
import com.dubizzle.network.GetItemsNetworkImpl
import com.dubizzle.service.GetItemsServiceImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.java.KoinAndroidApplication.create
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit



class DubizzleApp : Application() {

    private val koinModules = module {
        single { makeRetrofitService() }
        single { HttpRequestHelper() }
        single { BaseNetworkImpl() }
        single<IBaseNetwork> { BaseNetworkImpl() }
        factory<IGetItemsNetwork> { GetItemsNetworkImpl() }
        factory<IGetItemsService> { GetItemsServiceImpl() }
    }

    override fun onCreate() {
        super.onCreate()

        //initialising the koin application
        val koin = create(this)
            .modules(
                koinModules
            )
        startKoin(koin)
    }


    private fun makeRetrofitService(): INetworkApi {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(null)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build().create(INetworkApi::class.java)
    }
}
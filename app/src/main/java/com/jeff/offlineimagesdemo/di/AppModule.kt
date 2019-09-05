package com.jeff.offlineimagesdemo.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.jeff.offlineimagesdemo.data.Remote.RESTClient
import com.jeff.offlineimagesdemo.data.Remote.RemoteDataSource
import com.jeff.offlineimagesdemo.data.Remote.RemoteDataSourceImpl
import com.jeff.offlineimagesdemo.data.local.*
import com.jeff.offlineimagesdemo.di.UrlManager.Companion.BASE_URL
import com.jeff.offlineimagesdemo.repo.RepositoryImpl
import com.jeff.offlineimagesdemo.repo.Repository
import com.jeff.offlineimagesdemo.rx.BaseSchedulerProvider
import com.jeff.offlineimagesdemo.rx.SchedulerProvider
import okhttp3.OkHttpClient
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
 class AppModule {
    @Provides
    @Singleton
    fun getRestApi(): RESTClient
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(RESTClient::class.java)
    }

    @Provides
    @Singleton
    fun getBaseSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }


    @Provides
    @Singleton
    fun getRepository(localDataSource: LocalDataSource,
                      remoteDataSourceImpl: RemoteDataSource): Repository =
        RepositoryImpl(localDataSource, remoteDataSourceImpl)

    @Provides
    @Singleton
    fun getLocalDataSourceImpl(itemsDatabase:ItemsDatabase, schedulerProvider: BaseSchedulerProvider):LocalDataSource=
        LocalDataSourceImpl(itemsDatabase,schedulerProvider)

    @Provides
    fun gerItemsDatabase(context:Application):ItemsDatabase= ItemsDatabase.getInstance(context)

    @Provides
    fun getRemoteDataSource(restClient: RESTClient, schedulerProvider: BaseSchedulerProvider):RemoteDataSource=
        RemoteDataSourceImpl(restClient,schedulerProvider)
}
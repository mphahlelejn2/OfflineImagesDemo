package com.jeff.offlineimagesdemo.data.Remote

import android.annotation.SuppressLint
import com.jeff.offlineimagesdemo.data.LoadItemCallback
import io.reactivex.observers.DisposableSingleObserver
import com.jeff.offlineimagesdemo.data.LoadItemListCallback
import com.jeff.offlineimagesdemo.model.*
import com.jeff.offlineimagesdemo.rx.BaseSchedulerProvider
import io.reactivex.Maybe

class RemoteDataSourceImpl(private val restClient: RESTClient, private val schedulerProvider: BaseSchedulerProvider): RemoteDataSource {


    override fun loadItemDetails(imageId: String, loadItemCallback: LoadItemCallback) {
    }

    @SuppressLint("CheckResult")
    override fun loadItemList(callback: LoadItemListCallback)
    {
        restClient.getItemList()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeWith(object: DisposableSingleObserver<ServerRespond>(){
                override fun onSuccess(t: ServerRespond) {
                    callback.onItemListLoaded(t.data.images.imageList)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { callback.onError(it) }
                }

            })
    }

}
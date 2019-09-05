package com.jeff.offlineimagesdemo.repo

import android.annotation.SuppressLint
import com.jeff.offlineimagesdemo.data.LoadItemCallback
import com.jeff.offlineimagesdemo.data.LoadItemListCallback
import com.jeff.offlineimagesdemo.data.Remote.RemoteDataSource
import com.jeff.offlineimagesdemo.data.SaveCallBack
import com.jeff.offlineimagesdemo.data.local.LocalDataSource
import com.jeff.offlineimagesdemo.model.Item

class RepositoryImpl(private val localDataSource: LocalDataSource,
                     private val remoteDataSourceImpl: RemoteDataSource): Repository {

    override fun loadItemList(isInternetConnected: Boolean, callback: LoadItemListCallback){
        if(isInternetConnected)
            loadRemoteItemList(callback)
        else
            loadLocalArticleList(callback)
    }

    @SuppressLint("CheckResult")
    fun loadRemoteItemList(callback: LoadItemListCallback)
    {
        remoteDataSourceImpl.loadItemList(
            object : LoadItemListCallback {
                override fun onItemListLoaded(itemList: List<Item>) {
                    localDataSource.saveItemList(itemList,object:SaveCallBack{
                        override fun onComplete() {
                            loadLocalArticleList(callback)
                        }

                        override fun onError(toString: String) {
                            callback.onError(toString)
                        }

                    })
                }
                override fun onError(str:String) {
                    callback.onError(str)
                }
            }
        )
    }

    @SuppressLint("CheckResult")
    fun loadLocalArticleList(callback: LoadItemListCallback)
    {
        localDataSource.loadItemList(
            object : LoadItemListCallback {
                override fun onItemListLoaded(itemList: List<Item>) {
                    callback.onItemListLoaded(itemList)
                }
                override fun onError(str:String) {
                    callback.onError(str)
                }
            }
        )
    }

    override fun loadItemDetails(imageId: String, callback: LoadItemCallback){
            loadLocalArticleDetails(imageId, callback)
    }

    private fun loadLocalArticleDetails(
        imageId: String, callback: LoadItemCallback
    ) {
        localDataSource.loadItemDetails(imageId,object :
                LoadItemCallback {
                override fun onItemLoaded(itemDetails: Item) {
                    callback.onItemLoaded(itemDetails)
                }
                override fun onError(str: String) {
                    callback.onError(str)

                }
            } )
    }


}
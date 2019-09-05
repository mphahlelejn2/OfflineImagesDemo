package com.jeff.offlineimagesdemo.data.local
import android.annotation.SuppressLint
import com.jeff.offlineimagesdemo.data.LoadItemCallback
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import com.jeff.offlineimagesdemo.data.LoadItemListCallback
import com.jeff.offlineimagesdemo.data.SaveCallBack
import com.jeff.offlineimagesdemo.model.Item
import com.jeff.offlineimagesdemo.rx.BaseSchedulerProvider

class LocalDataSourceImpl(private val itemsDatabase:ItemsDatabase, private val schedulerProvider: BaseSchedulerProvider):LocalDataSource {

    @SuppressLint("CheckResult")
    override fun saveItemList(list: List<Item>, saveCallBack: SaveCallBack){

       Completable.fromAction {
           itemsDatabase.itemDao().saveItemList(list)
       }
           .subscribeOn(schedulerProvider.io())
           .observeOn(schedulerProvider.ui()).
               subscribeWith(object: DisposableCompletableObserver() {
               override fun onComplete() {
                   saveCallBack.onComplete()
               }

               override fun onError(e: Throwable) {
                   saveCallBack.onError(e.toString())
               }
           })
    }


    @SuppressLint("CheckResult")
    override fun loadItemList(callback: LoadItemListCallback)
    {
        itemsDatabase.itemDao().getItemList()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeWith(object: DisposableSingleObserver<List<Item>>(){
                override fun onSuccess(t: List<Item>) {
                    callback.onItemListLoaded(t)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { callback.onError(it) }
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun loadItemDetails(imageId: String, loadItemCallback: LoadItemCallback) {
        itemsDatabase.itemDao().getItem(imageId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeWith(object: DisposableSingleObserver<Item>(){
                override fun onSuccess(t: Item) {
                    loadItemCallback.onItemLoaded(t)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { loadItemCallback.onError(it) }
                } })
    }

}
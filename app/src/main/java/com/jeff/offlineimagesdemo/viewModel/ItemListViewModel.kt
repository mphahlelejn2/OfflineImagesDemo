package com.jeff.offlineimagesdemo.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeff.offlineimagesdemo.data.LoadItemListCallback
import com.jeff.offlineimagesdemo.model.Item
import com.jeff.offlineimagesdemo.repo.Repository


class ItemListViewModel(private val repository: Repository): ViewModel(){

    private var articleList= MutableLiveData<List<Item>>()
    private var errorMsg= MutableLiveData<String>()

    fun loadArticleList(isInternetConnected: Boolean) {
     repository.loadItemList(isInternetConnected, object :LoadItemListCallback {
         override fun onItemListLoaded(articlesList: List<Item>) {
             articlesList.sortedBy { item ->item.imageId}
             articleList.value=articlesList
         }
         override fun onError(str: String) {
             errorMsg.value=str
         }

     })
    }

    fun getArticleList(): LiveData<List<Item>> {
        return articleList
    }
    fun getErrorMsg():LiveData<String> {
        return errorMsg
    }
}

package com.jeff.offlineimagesdemo.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeff.offlineimagesdemo.data.LoadItemCallback
import com.jeff.offlineimagesdemo.model.Item
import com.jeff.offlineimagesdemo.repo.Repository

class ItemDetailsViewModel(private val repository: Repository): ViewModel(){

    private var itemDetails= MutableLiveData<Item>()
    private var errorMsg= MutableLiveData<String>()

    fun loadItemDetails( imageId: String) {
        repository.loadItemDetails( imageId,
            object : LoadItemCallback {
                override fun onItemLoaded(a: Item) {
                    itemDetails.value=a
                }
                override fun onError(str: String) {
                    errorMsg.value=str
                }
            })
    }

    fun getItemDetails(): LiveData<Item> {
        return itemDetails
    }
    fun getErrorMsg(): LiveData<String> {
        return errorMsg
    }
}
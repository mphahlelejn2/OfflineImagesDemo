package com.jeff.offlineimagesdemo.repo

import com.jeff.offlineimagesdemo.data.LoadItemCallback
import com.jeff.offlineimagesdemo.data.LoadItemListCallback

interface Repository {
    fun loadItemList(isInternetConnected: Boolean, callback: LoadItemListCallback)
    fun loadItemDetails(imageId: String, callback: LoadItemCallback)
}
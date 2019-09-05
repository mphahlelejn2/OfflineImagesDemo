package com.jeff.offlineimagesdemo.data

import com.jeff.offlineimagesdemo.model.Item

interface LoadItemCallback {
    fun onItemLoaded(itemList: Item)
    fun onError(str: String)
}
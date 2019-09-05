package com.jeff.offlineimagesdemo.data

import com.jeff.offlineimagesdemo.model.Item

interface LoadItemListCallback {
    fun onItemListLoaded(itemList: List<Item>)
    fun onError(str: String)
}
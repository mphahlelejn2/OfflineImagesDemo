package com.jeff.offlineimagesdemo.data


interface DataSource {
    fun loadItemList(callback: LoadItemListCallback)
    fun loadItemDetails(imageId: String, loadItemCallback: LoadItemCallback)
}
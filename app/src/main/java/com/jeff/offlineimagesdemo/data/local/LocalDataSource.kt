package com.jeff.offlineimagesdemo.data.local
import com.jeff.offlineimagesdemo.data.DataSource
import com.jeff.offlineimagesdemo.data.SaveCallBack
import com.jeff.offlineimagesdemo.model.Item

interface LocalDataSource:DataSource{
    fun saveItemList(list: List<Item>, saveCallBack: SaveCallBack)
}
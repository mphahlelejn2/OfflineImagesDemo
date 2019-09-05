package com.jeff.offlineimagesdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeff.offlineimagesdemo.model.Item
import io.reactivex.Single

@Dao
interface ItemDao {

    @Query("SELECT * FROM Item")
    fun getItemList():Single<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItemList(list:List<Item>)

    @Query("SELECT * FROM Item where imageId=:imageId")
    fun getItem(imageId: String
    ): Single<Item>

}
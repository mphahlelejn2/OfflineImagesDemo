package com.jeff.offlineimagesdemo.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeff.offlineimagesdemo.model.Item

@Database(entities = [Item::class],version = 2,exportSchema = false)
abstract class ItemsDatabase: RoomDatabase() {

    abstract fun itemDao():ItemDao

    //Demonstrate Singleton
    companion object{
        fun getInstance(context:Application):ItemsDatabase= Room.databaseBuilder(context,ItemsDatabase::class.java,"MyArticlesDatabase").build()
        }
    }

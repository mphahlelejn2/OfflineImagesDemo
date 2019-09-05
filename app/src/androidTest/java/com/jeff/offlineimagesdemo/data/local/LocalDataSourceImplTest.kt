package com.jeff.offlineimagesdemo.data.local

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.jeff.offlineimagesdemo.SchedulerProviderTest
import com.jeff.offlineimagesdemo.data.LoadItemListCallback
import com.jeff.offlineimagesdemo.model.Item
import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import android.R.attr.name
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff.offlineimagesdemo.data.TestData
import io.reactivex.observers.TestObserver
import io.reactivex.Maybe
import io.reactivex.Single
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class LocalDataSourceImplTest {

    @Mock
    private lateinit var itemsDatabase: ItemsDatabase
    private lateinit var scheduler: SchedulerProviderTest
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val context = InstrumentationRegistry.getTargetContext()
        itemsDatabase = Room.inMemoryDatabaseBuilder(context, ItemsDatabase::class.java!!).build()
        scheduler = SchedulerProviderTest.getInstance()
        localDataSource = LocalDataSourceImpl(itemsDatabase, scheduler)
    }


}
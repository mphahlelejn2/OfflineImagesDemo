package com.jeff.offlineimagesdemo.data.Remote

import com.jeff.offlineimagesdemo.SchedulerProviderTest
import com.jeff.offlineimagesdemo.data.LoadItemListCallback
import com.jeff.offlineimagesdemo.model.Data
import com.jeff.offlineimagesdemo.model.Images
import com.jeff.offlineimagesdemo.model.Item
import com.jeff.offlineimagesdemo.model.ServerRespond
import com.jeff.offlineimagesdemo.rx.BaseSchedulerProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response.error


class RemoteDataSourceImplTest {

    private lateinit var remoteDataSource:RemoteDataSource;
    lateinit var provider: BaseSchedulerProvider
    @Mock
    lateinit var client:RESTClient

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        provider= SchedulerProviderTest.getInstance()
        remoteDataSource=RemoteDataSourceImpl(client,provider);
    }

    @Test
    fun loadItemDetails_Error() {

        val exception:Exception = HttpException(
            error<Any>(403, ResponseBody.create(MediaType.parse("text/plain"), "Test Server Error")
            ))

        //When
        Mockito.`when`(client.getItemList())
            .thenReturn(Single.error(exception))

        var returnString=""
        remoteDataSource.loadItemList(object :LoadItemListCallback{
            override fun onItemListLoaded(itemList: List<Item>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(str: String) {
                returnString=str
            }
        })
        Assert.assertEquals(exception.message, returnString);
    }


    @Test
    fun loadItemDetails_Ok() {
        //When
        Mockito.`when`(client.getItemList())
            .thenReturn(Single.just(getRespondsSuccess()))

        var returnList= listOf<Item>()
        remoteDataSource.loadItemList(object :LoadItemListCallback{
            override fun onItemListLoaded(itemList: List<Item>) {
                returnList=itemList
            }

            override fun onError(str: String) {
            }
        })
        Assert.assertEquals(returnList, getRespondsSuccess().data.images.imageList);
    }

    private fun getRespondsSuccess():ServerRespond{

        var item1=Item("mg 1",
            "https://26.media.tumblr.com/tumblr_lr64gw71Ag1qjqdfao1_250.png",
            "https://thecatapi.com/?image_id=mg")

        var item2=Item("mg 2",
            "https://26.media.tumblr.com/tumblr_lr64gw71Ag1qjqdfao1_250.png",
            "https://thecatapi.com/?image_id=mg")

        var item3=Item("mg 3",
            "https://26.media.tumblr.com/tumblr_lr64gw71Ag1qjqdfao1_250.png",
            "https://thecatapi.com/?image_id=mg")

        return ServerRespond(Data(Images(listOf(item1,item2,item3))))
    }



}
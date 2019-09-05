package com.jeff.offlineimagesdemo.data.Remote

import io.reactivex.Single
import retrofit2.http.GET
import com.jeff.offlineimagesdemo.di.UrlManager
import com.jeff.offlineimagesdemo.model.*

public interface RESTClient {
    @GET(UrlManager.NORMAL_ENDPOINT)
    fun getItemList(): Single<ServerRespond>
}

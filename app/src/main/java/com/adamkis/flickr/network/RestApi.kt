package com.adamkis.flickr.network

import com.adamkis.flickr.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by adam on 2018. 01. 05..
 */
interface RestApi {

    @GET("?method=flickr.photos.getRecent")
    fun getRecentPhotos(): Call<PhotosResponse>

}
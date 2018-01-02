package com.adamkis.flickr.network

import com.adamkis.flickr.helper.SecretKeys
import com.adamkis.flickr.model.Model
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by adam on 2018. 01. 02..
 */


import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface WikiApiService {

    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String): Observable<Model.Result>

    companion object {
        fun create(): WikiApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://en.wikipedia.org/w/")
                    .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }

}

//object UrlHelper {
//
////    working example: https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=c27ed21e71d19b4aa101ed081596a74c&format=json&nojsoncallback=1
//
//    val URL_BASE = ("https://api.flickr.com/services/rest/?format=json&nojsoncallback=1&api_key=" + SecretKeys.FLICKR_KEY)
//    val URL_SEARCH = "&method=flickr.photos.getPopular"
//}
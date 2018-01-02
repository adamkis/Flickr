package com.adamkis.flickr.network


/**
 * Created by adam on 2018. 01. 02..
 */


import com.adamkis.flickr.helper.SecretKeys
import com.adamkis.flickr.model.PhotosResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor



interface FlikcrApiService {

    @GET("?method=flickr.photos.getRecent")
    fun getRecentPhotos(): Call<PhotosResponse>

    companion object {
        fun create(): FlikcrApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val queryParamInterceptor = Interceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder()
                        .addQueryParameter("format", "json")
                        .addQueryParameter("nojsoncallback", "1")
                        .addQueryParameter("api_key", SecretKeys.FLICKR_KEY)
                        .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(queryParamInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.flickr.com/services/rest/" )
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            return retrofit.create(FlikcrApiService::class.java)
        }
    }

}

fun <T> callback(success: (Response<T>) -> Unit, failure: (t: Throwable) -> Unit): Callback<T>? {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) = success(response)
        override fun onFailure(call: Call<T>, t: Throwable) = failure(t)
    }
}
package com.adamkis.flickr.dagger

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 07..
 */
@Module
class RetrofitModule(private val baseUrl: String) {
    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build()
    }
}
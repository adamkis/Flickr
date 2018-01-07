package com.adamkis.flickr.dagger

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Module
class OkHttpModule() {
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .build()
    }
}
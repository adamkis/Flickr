package com.adamkis.flickr.dagger

import com.adamkis.flickr.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import javax.inject.Named


/**
 * Created by adam on 2018. 01. 05..
 */
@Module
class OkHttpModule() {
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, @Named("flickr") flickrInterceptor: Interceptor, @Named("token") tokenInterceptor: Interceptor): OkHttpClient {
        var builder = OkHttpClient.Builder()
                .addInterceptor(flickrInterceptor)
                .addInterceptor(tokenInterceptor)
        if (BuildConfig.DEBUG){
            builder = builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }
}
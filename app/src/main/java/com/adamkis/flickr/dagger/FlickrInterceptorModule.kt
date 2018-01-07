package com.adamkis.flickr.dagger

import com.adamkis.flickr.helper.SecretKeys
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 07..
 */
@Module
class FlickrInterceptorModule() {
    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("format", "json")
                    .addQueryParameter("nojsoncallback", "1")
                    .addQueryParameter("api_key", SecretKeys.FLICKR_KEY)
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
}
package com.adamkis.flickr.dagger

import com.adamkis.flickr.helper.SecretKeys
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 07..
 */
@Module
class TokenInterceptorModule() {
    @Provides
    @Singleton
    @Named("token")
    fun provideTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            // TODO Implement proper request: Now it's only a stub
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("random", "random")
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
}
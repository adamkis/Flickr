package com.adamkis.flickr.dagger

import com.adamkis.flickr.helper.SecretKeys
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 07..
 */
@Module
class ApiKeyInterceptorModule() {
    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("api_key", SecretKeys.FLICKR_KEY)
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
}
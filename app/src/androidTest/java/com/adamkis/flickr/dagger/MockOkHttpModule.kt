package com.adamkis.flickr.dagger

import com.adamkis.flickr.helper.MockResponseStrings
import com.adamkis.flickr.helper.TestUtils
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Module
class MockOkHttpModule() {
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, @Named("format") interceptor: Interceptor): OkHttpClient {
        val responseInterceptor = object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                return Response.Builder()
                        .code(200)
                        .message(MockResponseStrings.MOCK_RESPONSE_GETRECENT)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_0)
                        .body(ResponseBody.create(MediaType.parse("application/json"), MockResponseStrings.MOCK_RESPONSE_GETRECENT.toByteArray()))
                        .addHeader("content-type", "application/json")
                        .build()
            }
        }
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .addInterceptor(responseInterceptor)
                .build()
    }
}
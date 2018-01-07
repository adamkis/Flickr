package com.adamkis.flickr.dagger

import com.adamkis.flickr.TestUtils
import com.adamkis.flickr.network.RestApi
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Module
class MockNetModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient {
        val responseInterceptor = object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                return Response.Builder()
                        .code(200)
                        .message(TestUtils.MOCK_RESPONSE_GETRECENT)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_0)
                        .body(ResponseBody.create(MediaType.parse("application/json"), TestUtils.MOCK_RESPONSE_GETRECENT.toByteArray()))
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

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi {
        return retrofit.create<RestApi>(RestApi::class.java)
    }

}
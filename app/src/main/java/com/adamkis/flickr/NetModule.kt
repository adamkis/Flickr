package com.adamkis.flickr

import com.adamkis.flickr.helper.SecretKeys
import com.adamkis.flickr.network.RestApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Module
class NetModule(val mBaseUrl: String) {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // TODO get these interceptors out
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

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(queryParamInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(mBaseUrl)
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
package com.adamkis.flickr.dagger.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 07..
 */
@Module
class GlideModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideGlide(): RequestManager {
        return Glide.with(context)
    }
}
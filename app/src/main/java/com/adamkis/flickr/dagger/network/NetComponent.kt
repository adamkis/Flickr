package com.adamkis.flickr.dagger.network

import com.adamkis.flickr.ui.activity.MainActivity
import com.adamkis.flickr.ui.fragment.RecentsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Singleton
@Component(modules = arrayOf(
        OkHttpModule::class,
        FormatInterceptorModule::class,
        ApiKeyInterceptorModule::class,
        GsonConverterFactoryModule::class,
        LoggingInterceptorModule::class,
        RestApiModule::class,
        RetrofitModule::class))
interface NetComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(recentsFragment: RecentsFragment)
}

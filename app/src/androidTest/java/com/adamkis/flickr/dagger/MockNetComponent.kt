package com.adamkis.flickr.dagger

import com.adamkis.flickr.dagger.network.*
import com.adamkis.flickr.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Singleton
@Component(modules = arrayOf(
        MockOkHttpModule::class,
        FormatInterceptorModule::class,
        GsonConverterFactoryModule::class,
        LoggingInterceptorModule::class,
        RestApiModule::class,
        RetrofitModule::class))
interface MockNetComponent : NetComponent {
    override fun inject(activity: MainActivity)
}
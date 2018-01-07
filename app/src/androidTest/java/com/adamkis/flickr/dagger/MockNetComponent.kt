package com.adamkis.flickr.dagger

import com.adamkis.flickr.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Singleton
@Component(modules = arrayOf(
        MockNetModule::class,
        FlickrInterceptorModule::class,
        GsonConverterFactoryModule::class,
        LoggingInterceptorModule::class))
interface MockNetComponent : NetComponent {
    override fun inject(activity: MainActivity)
}
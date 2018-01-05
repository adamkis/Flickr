package com.adamkis.flickr

import dagger.Component
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {
    fun inject(activity: MainActivity)
}

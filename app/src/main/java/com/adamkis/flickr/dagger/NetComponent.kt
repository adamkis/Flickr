package com.adamkis.flickr.dagger

import com.adamkis.flickr.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Singleton
@Component(modules = arrayOf(NetModule::class))
interface NetComponent {
    fun inject(activity: MainActivity)
}
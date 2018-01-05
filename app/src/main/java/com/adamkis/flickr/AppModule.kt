package com.adamkis.flickr

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Module
class AppModule(internal var mApplication: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }
}
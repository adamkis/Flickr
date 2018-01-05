package com.adamkis.flickr

import android.app.Application


/**
 * Created by adam on 2018. 01. 05..
 */
class App : Application() {

    companion object {
        lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()
        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("https://api.flickr.com/services/rest/"))
                .build()
    }

}
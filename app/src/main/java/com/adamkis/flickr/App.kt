package com.adamkis.flickr

import android.app.Application
import android.support.annotation.VisibleForTesting
import com.adamkis.flickr.dagger.*
import com.adamkis.flickr.network.FLICKR_URL_BASE


/**
 * Created by adam on 2018. 01. 05..
 */
class App : Application() {

    @VisibleForTesting
    companion object {
        lateinit var netComponent: NetComponent
    }

    fun setNetComponent(netComponent: NetComponent){
        App.netComponent = netComponent
    }

    fun createComponent(baseUrl: String): NetComponent {
        return DaggerNetComponent.builder()
                .okHttpModule(OkHttpModule())
                .formatInterceptorModule(FormatInterceptorModule())
                .apiKeyInterceptorModule(ApiKeyInterceptorModule())
                .gsonConverterFactoryModule(GsonConverterFactoryModule())
                .loggingInterceptorModule(LoggingInterceptorModule())
                .restApiModule(RestApiModule())
                .retrofitModule(RetrofitModule(baseUrl))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        netComponent = createComponent(FLICKR_URL_BASE)
    }

}
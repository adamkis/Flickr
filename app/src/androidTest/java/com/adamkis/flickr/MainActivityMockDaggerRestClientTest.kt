package com.adamkis.flickr

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.adamkis.flickr.ui.MainActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.adamkis.flickr.dagger.*
import com.adamkis.flickr.network.FLICKR_URL_BASE
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by adam on 2017. 12. 31..
 */
class MainActivityMockDaggerRestClientTest {


    @Suppress("unused") // actually used by Espresso
    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, false, false)
    val app by lazy { InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App }

    @Before
    fun setUp() {
        val mockNetComponent =  DaggerMockNetComponent.builder()
                .mockOkHttpModule(MockOkHttpModule())
                .formatInterceptorModule(FormatInterceptorModule())
                .gsonConverterFactoryModule(GsonConverterFactoryModule())
                .loggingInterceptorModule(LoggingInterceptorModule())
                .restApiModule(RestApiModule())
                .retrofitModule(RetrofitModule(FLICKR_URL_BASE))
                .build()
        app.setNetComponent(mockNetComponent)
        activityRule.launchActivity(Intent())
    }

    @Test
    fun homeActivity_firstPhotoTitleFound() {
        onView(withText("Pukaskwa Coastal Trail Aug-Sept 2017")).check(matches(isDisplayed()))
    }

}
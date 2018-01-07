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
class MainActivityRESTMockDaggerTest {


    @Suppress("unused") // actually used by Espresso
    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, false, false)
    val app by lazy { InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App }

    @Before
    fun setUp() {
        val mockNetComponent =  DaggerMockNetComponent.builder()
                .mockNetModule(MockNetModule(FLICKR_URL_BASE))
                .flickrInterceptorModule(FlickrInterceptorModule())
                .gsonConverterFactoryModule(GsonConverterFactoryModule())
                .loggingInterceptorModule(LoggingInterceptorModule())
                .build()
        app.setNetComponent(mockNetComponent)
        activityRule.launchActivity(Intent())
    }

    @Test
    fun homeActivity_isMessage2TextFound() {
        onView(withText("Pukaskwa Coastal Trail Aug-Sept 2017")).check(matches(isDisplayed()))
    }

    @Test
    fun homeActivity_isMessage2TextInMessage2Container() {
        onView(ViewMatchers.withId(R.id.message2)).check(matches(withText("Pukaskwa Coastal Trail Aug-Sept 2017")))
    }

}
package com.adamkis.flickr

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule
import com.adamkis.flickr.model.PhotosResponse
import com.adamkis.flickr.network.RestApi
import com.adamkis.flickr.ui.MainActivity
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.verify
//import retrofit2.Call
//import org.mockito.Mockito
//import android.support.test.InstrumentationRegistry.getTargetContext
import android.util.Log
import com.adamkis.flickr.dagger.NetModule
import org.junit.*


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.adamkis.flickr.dagger.DaggerMockNetComponent
import com.adamkis.flickr.dagger.MockNetModule
import com.squareup.okhttp.mockwebserver.Dispatcher
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.squareup.okhttp.mockwebserver.RecordedRequest
import org.junit.After
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

//    @Mock private var restApi: RestApi? = null
//    var mockedCall: Call<PhotosResponse> = Mockito.mock(Call<PhotosResponse>!!::class.java)

    @Test
    fun testRestMockDagger() {

//        app.setNetComponent(app.createComponent(server.url("/").toString()))
//
//        var call: Call<PhotosResponse> = Call()
//        `when`(restApi.getRecentPhotos().enqueue()).thenReturn("abc")
//
//        activityRule.launchActivity(null)
//
//        verify(myPrinter).print("ABC")
    }

//    @Suppress("unused") // actually used by Espresso
//    @get:Rule
//    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)
//    lateinit private var server: MockWebServer
//    val app by lazy { InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App }

    @Before
    fun setUp() {

        val mockNetComponent =  DaggerMockNetComponent.builder()
                .mockNetModule(MockNetModule("https://api.flickr.com/services/rest/"))
                .build()
        app.setNetComponent(mockNetComponent)
        activityRule.launchActivity(Intent())
    }

    @After
    fun tearDown() {
//        server.shutdown()
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
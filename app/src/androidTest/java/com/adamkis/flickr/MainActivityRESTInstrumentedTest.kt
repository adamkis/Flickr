package com.adamkis.flickr

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
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
class MainActivityRESTInstrumentedTest {

    @Suppress("unused") // actually used by Espresso
    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)
    lateinit private var server: MockWebServer
    val app by lazy { InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App }

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path.startsWith("/?method=flickr.photos.getRecent")) {
                    return MockResponse().setResponseCode(200).setBody(TestUtils.MOCK_RESPONSE_GETRECENT)
                }
                return MockResponse().setResponseCode(404)
            }
        }
        server.setDispatcher(dispatcher)
        app.setNetComponent(app.createComponent(server.url("/").toString()))
        val intent = Intent()
        mActivityRule.launchActivity(intent)
    }

    @After
    fun tearDown() {
        server.shutdown()
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
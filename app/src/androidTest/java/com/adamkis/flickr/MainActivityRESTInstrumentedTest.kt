package com.adamkis.flickr

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.InstrumentationTestCase
import com.adamkis.flickr.network.FLICKR_URL_BASE
import com.squareup.okhttp.mockwebserver.Dispatcher
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.squareup.okhttp.mockwebserver.RecordedRequest

/**
 * Created by adam on 2017. 12. 31..
 */
@RunWith(AndroidJUnit4::class)
class MainActivityRESTInstrumentedTest : InstrumentationTestCase() {

    @Suppress("unused") // actually used by Espresso
    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)
    lateinit private var server: MockWebServer

    @Before
    @Throws(Exception::class)
    override public fun setUp() {
        super.setUp()
        server = MockWebServer()
        server.start()
        injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        FLICKR_URL_BASE = server.url("/").toString()
    }


    @After
    @Throws(Exception::class)
    public override fun tearDown() {
        server.shutdown()
    }


    @Test
    @Throws(Exception::class)
    fun homeActivity_isCorrectTitleShown() {
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

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withText("Pukaskwa Coastal Trail Aug-Sept 2017")).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.message2)).check(matches(withText("Pukaskwa Coastal Trail Aug-Sept 2017")))
    }

}
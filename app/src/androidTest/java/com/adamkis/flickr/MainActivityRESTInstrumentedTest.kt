package com.adamkis.flickr

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.InstrumentationTestCase
import com.adamkis.flickr.network.FLICKR_URL_BASE
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by adam on 2017. 12. 31..
 */
@RunWith(AndroidJUnit4::class)
class MainActivityRESTInstrumentedTest : InstrumentationTestCase() {

    @Suppress("unused") // actually used by Espresso
    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)
    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    override public fun setUp() {
        super.setUp()
        server = MockWebServer()
        server!!.start()
        injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        FLICKR_URL_BASE = server!!.url("/").toString()
    }


    @After
    @Throws(Exception::class)
    public override fun tearDown() {
        server!!.shutdown()
    }


    @Test
    @Throws(Exception::class)
    fun homeActivity_isCorrectTitleShown() {
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(TestUtils.MOCK_RESPONSE_GETRECENT))
        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withText("Pukaskwa Coastal Trail Aug-Sept 2017")).check(matches(isDisplayed()))
    }

}
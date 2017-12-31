package com.adamkis.flickr

import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import org.junit.Rule


/**
 * Created by adam on 2017. 12. 31..
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Suppress("unused") // actually used by Espresso
    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun homeActivity_Displayed() {
        onView(withId(R.id.message)).check(matches(withText("Home")))
    }
}

package com.adamkis.flickr.helper

import android.support.test.InstrumentationRegistry
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


/**
 * Created by adam on 2017. 12. 31..
 */


object TestUtils{

    fun getString(id: Int): String {
        return InstrumentationRegistry.getTargetContext().resources.getString(id)
    }

}
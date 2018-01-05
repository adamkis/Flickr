package com.adamkis.flickr.network


/**
 * Created by adam on 2018. 01. 02..
 */

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Only modify it from API test cases
var FLICKR_URL_BASE = "https://api.flickr.com/services/rest/"

fun <T> callback(success: (Response<T>) -> Unit, failure: (t: Throwable) -> Unit): Callback<T>? {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) = success(response)
        override fun onFailure(call: Call<T>, t: Throwable) = failure(t)
    }
}
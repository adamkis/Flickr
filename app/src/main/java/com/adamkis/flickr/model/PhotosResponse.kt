package com.adamkis.flickr.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by adam on 2017. 01. 14..
 */
data class PhotosResponse(var photos: Photos? = null, var stat: String? = null)
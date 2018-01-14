package com.adamkis.flickr.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by adam on 2017. 01. 14..
 */
data class Photos(var total: String? = null, var page: String? = null, var pages: String? = null, var photo: Array<Photo>? = null, var perpage: String? = null)
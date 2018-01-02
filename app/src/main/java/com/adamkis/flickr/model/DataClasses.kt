package com.adamkis.flickr.model

/**
 * Created by adam on 2017. 12. 31..
 */


//data class Photo(var isfamily: String? = null, var farm: String? = null, var id: String? = null, var title: String? = null,
//                 var ispublic: String? = null, var owner: String? = null, var secret: String? = null, var server: String? = null, var isfriend: String? = null)
//
//data class Photos(var total: String? = null, var page: String? = null, var pages: String? = null, var photo: Array<Photo>? = null, var perpage: String? = null)
//
//data class PhotosResponse(var photos: Photos? = null, var stat: String? = null)

object Model {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}
package com.adamkis.flickr.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.adamkis.flickr.App
import com.adamkis.flickr.R
import com.adamkis.flickr.model.PhotosResponse
import com.adamkis.flickr.network.RestApi
import com.adamkis.flickr.network.getStackTrace
import com.adamkis.flickr.ui.adapter.RecentsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class RecentsFragment : BaseFragment() {

    @Inject lateinit var restApi: RestApi
    private var clickDisposable: Disposable? = null
    private var callDisposable: Disposable? = null
    private var photosResponse: PhotosResponse? = null
    private val PHOTOS_RESPONSE_KEY = "PHOTOS_RESPONSE_KEY"

    companion object {
        fun newInstance(): RecentsFragment {
            val fragment = RecentsFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        App.netComponent.inject(this)
        return inflater.inflate(R.layout.fragment_recents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLoadingAndError(view.findViewById(R.id.loading), view as CoordinatorLayout)
        val recentsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recents_recycler_view)
        photosResponse = savedInstanceState?.getParcelable(PHOTOS_RESPONSE_KEY)
        if(photosResponse != null){
            setUpAdapter(recentsRecyclerView, photosResponse!!)
            showLoading(false)
        }
        else{
            downloadData(recentsRecyclerView)
        }
    }

    private fun downloadData(recentsRecyclerView: RecyclerView){
        callDisposable = restApi.getRecentPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doAfterTerminate { showLoading(false) }
            .subscribe(
                {photosResponse ->
                    this@RecentsFragment.photosResponse = photosResponse
                    setUpAdapter(recentsRecyclerView, photosResponse)
                },
                {t ->
                    when(t){
                        is UnknownHostException -> {
                            showError(getString(R.string.network_error))
                        }
                        is NullPointerException -> {
                            showError(getString(R.string.could_not_load_data))
                        }
                        else -> {
                            showError(getString(R.string.error))
                        }
                    }
                }
            )
    }

    private fun setUpAdapter(recentsRecyclerView: RecyclerView, photosResponse: PhotosResponse){
        recentsRecyclerView.layoutManager = LinearLayoutManager(this@RecentsFragment.activity, LinearLayout.VERTICAL, false)
        recentsRecyclerView.adapter = RecentsAdapter(photosResponse.photos!!, activity as Context)
        clickDisposable = (recentsRecyclerView.adapter as RecentsAdapter).clickEvent
                .subscribe({
                    startDetailActivityWithTransition(activity as Activity, it.second.findViewById(R.id.recents_image), it.second.findViewById(R.id.recents_photo_id), it.first)
                })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(PHOTOS_RESPONSE_KEY, photosResponse)
    }

    override fun onDestroy() {
        super.onDestroy()
        clickDisposable?.dispose()
        callDisposable?.dispose()
    }

}

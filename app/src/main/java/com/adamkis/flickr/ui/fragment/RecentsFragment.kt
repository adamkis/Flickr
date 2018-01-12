package com.adamkis.flickr.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.adamkis.flickr.App
import com.adamkis.flickr.R
import com.adamkis.flickr.model.Photo
import com.adamkis.flickr.model.PhotosResponse
import com.adamkis.flickr.network.RestApi
import com.adamkis.flickr.network.getStackTrace
import com.adamkis.flickr.ui.activity.PhotoDetailActivity
import com.adamkis.flickr.ui.adapter.RecentsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class RecentsFragment : Fragment() {

    @Inject lateinit var restApi: RestApi
    private var clickDisposable: Disposable? = null
    private var callDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        App.netComponent.inject(this)
        return inflater.inflate(R.layout.fragment_recents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recentsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recentsRecyclerView)

        callDisposable = restApi.getRecentPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {r ->
                        Timber.i( "First Title " + r.photos!!.photo!![0].title)
                        setUpAdapter(recentsRecyclerView, r)
                    },
                    {t ->
                        when(t){
                            is UnknownHostException -> {
                                Toast.makeText(this@RecentsFragment.activity, t.toString(), Toast.LENGTH_SHORT).show()
                                Timber.d("UnknownHostException" + getStackTrace(t))
                            }
                            is NullPointerException -> {
                                Toast.makeText(this@RecentsFragment.activity, t.toString(), Toast.LENGTH_SHORT).show()
                                Timber.d("NullPointerException" + getStackTrace(t))
                            }
                            else -> {
                                Timber.d("Exception caught " + getStackTrace(t))
                            }
                        }
                    }
                )

    }

    fun setUpAdapter(recentsRecyclerView: RecyclerView, r: PhotosResponse){
        recentsRecyclerView.layoutManager = LinearLayoutManager(this@RecentsFragment.activity, LinearLayout.VERTICAL, false)
        recentsRecyclerView.adapter = RecentsAdapter(r.photos!!, activity as Context)
        clickDisposable = (recentsRecyclerView.adapter as RecentsAdapter).clickEvent
                .subscribe({
                    context?.startActivity(Intent(context, PhotoDetailActivity::class.java))
                    var photo: Photo = it
                    Toast.makeText(context, photo.title, Toast.LENGTH_LONG).show()
                })
    }


    override fun onDestroy() {
        super.onDestroy()
        clickDisposable?.dispose()
        callDisposable?.dispose()
    }

    companion object {
        fun newInstance(): RecentsFragment {
            val fragment = RecentsFragment()
            return fragment
        }
    }
}

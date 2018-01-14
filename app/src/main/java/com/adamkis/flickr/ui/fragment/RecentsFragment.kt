package com.adamkis.flickr.ui.fragment

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.adamkis.flickr.App
import com.adamkis.flickr.R
import com.adamkis.flickr.helper.FilePersistenceHelper
import com.adamkis.flickr.helper.TransitionHelper
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
        val recentsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recents_recycler_view)

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
                    startDetailActivityWithTransition(activity as Activity, it.second.findViewById(R.id.recents_image), it.second.findViewById(R.id.recents_photo_id), it.first)
                })
    }


    override fun onDestroy() {
        super.onDestroy()
        clickDisposable?.dispose()
        callDisposable?.dispose()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startDetailActivityWithTransition(activity: Activity, firstViewToAnimate: View, secondViewToAnimate: View, photo: Photo) {

        val animationBundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                *TransitionHelper.createSafeTransitionParticipants(activity,
                        false,
                        Pair(firstViewToAnimate, activity.getString(R.string.transition_recents_photo_image)),
                        Pair(secondViewToAnimate, activity.getString(R.string.transition_recents_photo_id))
                ))
                .toBundle()
        try {
            FilePersistenceHelper.writeBitmapToFile(activity, ((firstViewToAnimate as ImageView).drawable as BitmapDrawable).bitmap)
        } catch (e: TypeCastException) {
            Timber.d(getStackTrace(e)) // This happens when the image hasn't loaded yet, not saving is enough
        }
        val startIntent = PhotoDetailActivity.getStartIntent(activity, photo)
        startActivity(startIntent, animationBundle)
    }


}

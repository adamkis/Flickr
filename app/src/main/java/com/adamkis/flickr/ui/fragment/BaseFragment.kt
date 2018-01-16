package com.adamkis.flickr.ui.fragment

import android.annotation.TargetApi
import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.view.View
import android.widget.ImageView
import com.adamkis.flickr.R
import com.adamkis.flickr.helper.FilePersistenceHelper
import com.adamkis.flickr.helper.TransitionHelper
import com.adamkis.flickr.model.Photo
import com.adamkis.flickr.network.getStackTrace
import com.adamkis.flickr.ui.activity.PhotoDetailActivity
import timber.log.Timber

/**
 * Created by adam on 2018. 01. 16..
 */
abstract class BaseFragment : Fragment(){

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun startDetailActivityWithTransition(activity: Activity, firstViewToAnimate: View, secondViewToAnimate: View, photo: Photo) {
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
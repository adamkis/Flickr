package com.adamkis.flickr.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamkis.flickr.R
import com.adamkis.flickr.model.Photo
import kotlinx.android.synthetic.main.fragment_photo_detail.*

/**
 * Created by adam on 2018. 01. 11..
 */
class PhotoDetailFragment : Fragment() {

    private var photo: Photo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            photo = arguments!!.getParcelable(ARG_PHOTO)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recents_photo_id.text = photo?.id
    }

    companion object {

        private val ARG_PHOTO = "ARG_PHOTO"

        fun newInstance(photo: Photo): PhotoDetailFragment {
            val fragment = PhotoDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_PHOTO, photo)
            fragment.arguments = args
            return fragment
        }

    }

}
package com.adamkis.flickr.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import com.adamkis.flickr.App
import com.adamkis.flickr.R
import com.adamkis.flickr.network.RestApi
import com.adamkis.flickr.network.callback
import com.adamkis.flickr.ui.adapter.RecentsAdapter
import timber.log.Timber
import javax.inject.Inject

class RecentsFragment : Fragment() {

    @Inject lateinit var restApi: RestApi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        App.netComponent.inject(this)
        return inflater.inflate(R.layout.fragment_recents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recentsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recentsRecyclerView)

        restApi.getRecentPhotos().enqueue(callback(
                {r ->
                    Timber.i( "First Title " + r.body()!!.photos!!.photo!![0].title)
                    recentsRecyclerView.layoutManager = LinearLayoutManager(this@RecentsFragment.activity, LinearLayout.VERTICAL, false)
                    recentsRecyclerView.adapter = RecentsAdapter(r.body()!!.photos!!, activity as Context)
                },
                {t -> Toast.makeText(this@RecentsFragment.activity, t.toString(), Toast.LENGTH_SHORT).show()}))


    }

    companion object {
        fun newInstance(): RecentsFragment {
            val fragment = RecentsFragment()
            return fragment
        }
    }
}

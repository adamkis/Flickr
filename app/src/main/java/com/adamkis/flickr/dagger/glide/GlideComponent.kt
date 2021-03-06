package com.adamkis.flickr.dagger.glide

import android.support.v7.widget.RecyclerView
import com.adamkis.flickr.ui.adapter.RecentsAdapter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by adam on 2018. 01. 05..
 */
@Singleton
@Component(modules = arrayOf(
        GlideModule::class))
interface GlideComponent {
    fun inject(viewHolder: RecyclerView.ViewHolder)
    fun inject(recentsAdapter: RecentsAdapter)
}
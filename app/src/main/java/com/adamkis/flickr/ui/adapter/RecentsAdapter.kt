package com.adamkis.flickr.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamkis.flickr.App
import com.adamkis.flickr.R
import com.adamkis.flickr.model.Photo
import com.adamkis.flickr.model.Photos
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.recents_item.view.*
import javax.inject.Inject

/**
 * Created by adam on 2018. 01. 09..
 */
class RecentsAdapter(val photos: Photos) : RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder>(){

    @Inject lateinit var glideReqManager: RequestManager

    init {
        App.glideComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecentsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recents_item, parent, false)
        return RecentsViewHolder(glideReqManager, view)
    }

    override fun onBindViewHolder(holder: RecentsViewHolder?, position: Int) {
        holder?.bind(photos?.photo?.get(position))
    }

    override fun getItemCount(): Int = photos?.photo?.size ?: 0

    class RecentsViewHolder(val glideReqManager: RequestManager, itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(photo: Photo?){
            itemView.recentsText.text = photo?.title
            glideReqManager.load(photo?.getUrl()).into(itemView.findViewById(R.id.image))
        }

    }

}
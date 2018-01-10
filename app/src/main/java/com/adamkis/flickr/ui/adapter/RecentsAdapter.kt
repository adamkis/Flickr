package com.adamkis.flickr.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamkis.flickr.R
import com.adamkis.flickr.model.Photo
import com.adamkis.flickr.model.Photos
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recents_item.view.*

/**
 * Created by adam on 2018. 01. 09..
 */
class RecentsAdapter(val photos: Photos, val context: Context) : RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecentsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recents_item, parent, false)
        return RecentsViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: RecentsViewHolder?, position: Int) {
        holder?.bind(photos?.photo?.get(position))
    }

    override fun getItemCount(): Int = photos?.photo?.size ?: 0

    class RecentsViewHolder(val context: Context, itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(photo: Photo?){
            itemView.recentsText.text = photo?.title
            Glide.with(context).load(photo?.getUrl()).into(itemView.findViewById(R.id.image))
        }

    }

}
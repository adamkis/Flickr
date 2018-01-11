package com.adamkis.flickr.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adamkis.flickr.R
import com.adamkis.flickr.ui.fragment.PhotoDetailFragment

/**
 * Created by adam on 2018. 01. 11..
 */
class PhotoDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        supportFragmentManager.beginTransaction().replace(R.id.container, PhotoDetailFragment.newInstance()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
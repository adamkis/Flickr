package com.adamkis.flickr.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.adamkis.flickr.R
import com.adamkis.flickr.model.Photo
import com.adamkis.flickr.ui.fragment.PhotoDetailFragment
import kotlinx.android.synthetic.main.activity_photo_detail.*
import timber.log.Timber

/**
 * Created by adam on 2018. 01. 11..
 */
class PhotoDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        val photo: Photo = intent.getParcelableExtra(Photo.TAG)
        (photoTitle as TextView).text = photo.title
        Timber.d("PhotoDetailActivity.onCreate: " + photo.title)

        supportFragmentManager.beginTransaction().replace(R.id.container, PhotoDetailFragment.newInstance()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    companion object {
        fun getStartIntent(context: Context, photo: Photo): Intent {
            return Intent(context, PhotoDetailActivity::class.java)
                    .apply { putExtra(Photo.TAG, photo) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
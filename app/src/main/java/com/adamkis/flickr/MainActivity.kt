package com.adamkis.flickr

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.adamkis.flickr.model.PhotosResponse
import com.adamkis.flickr.network.FlikcrApiService
import com.adamkis.flickr.network.callback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call


class MainActivity : AppCompatActivity() {

    val flickrApiService by lazy {
        FlikcrApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        flickrApiService.getRecentPhotos().enqueue(callback(
            {r ->
                Log.i("Flickr", "Yooooooo " + r.body()!!.photos!!.photo!![0].title)
                message2.text = r.body()!!.photos!!.photo!![0].title
            },
            {t -> Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()}))
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}

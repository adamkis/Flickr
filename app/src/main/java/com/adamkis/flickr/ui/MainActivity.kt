package com.adamkis.flickr.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.adamkis.flickr.App
import com.adamkis.flickr.R
import com.adamkis.flickr.network.RestApi
import com.adamkis.flickr.network.callback
import com.adamkis.flickr.ui.adapter.RecentsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var restApi: RestApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.netComponent.inject(this)

        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportActionBar?.title = getString(R.string.title_home)

        restApi.getRecentPhotos().enqueue(callback(
            {r ->
                Timber.i( "First Title " + r.body()!!.photos!!.photo!![0].title)
                recentsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                recentsRecyclerView.adapter = RecentsAdapter(r.body()!!.photos!!)
            },
            {t -> Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()}))

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                this@MainActivity.supportActionBar?.title = getString(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                this@MainActivity.supportActionBar?.title = getString(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                this@MainActivity.supportActionBar?.title = getString(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}

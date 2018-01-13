package com.adamkis.flickr.ui.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.adamkis.flickr.R
import kotlinx.android.synthetic.main.activity_temp.*

/**
 * Created by adam on 2018. 01. 13..
 */
class TempActivity : AppCompatActivity() {

    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)


        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        collapsingToolbarLayout!!.title = "TempTitle"

//        dynamicToolbarColor()

//        toolbarTextAppernce()
    }


    private fun dynamicToolbarColor() {

        val bitmap = BitmapFactory.decodeResource(resources,
                R.drawable.dog)
        Palette.from(bitmap).generate { palette ->
            collapsingToolbarLayout!!.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary))
            collapsingToolbarLayout!!.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark))
        }
    }


    private fun toolbarTextAppernce() {
        collapsingToolbarLayout!!.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        collapsingToolbarLayout!!.setExpandedTitleTextAppearance(R.style.expandedappbar)
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}
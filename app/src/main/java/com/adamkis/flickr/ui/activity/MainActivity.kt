package com.adamkis.flickr.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.adamkis.flickr.R
import com.adamkis.flickr.ui.fragment.RecentsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportActionBar?.title = getString(R.string.title_home)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, RecentsFragment.newInstance()).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                this@MainActivity.supportActionBar?.title = getString(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                this@MainActivity.supportActionBar?.title = getString(R.string.title_dashboard)
                startActivity(Intent(this@MainActivity, TempActivity::class.java))
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

package com.shivam.marvelgallery.ui

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.data.adapters.ViewPagerAdapter
import com.shivam.marvelgallery.presenter.MarvelGalleryDelegate
import kotlinx.android.synthetic.main.activity_marvel_gallery.*

class MarvelGalleryActivity : AppCompatActivity(), MarvelGalleryDelegate {

    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_gallery)
        setSupportActionBar(toolbar)
        toolbar?.setTitle(R.string.app_name)
        setUpViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_marvel_gallery, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setUpViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        view_pager_star_wars.adapter = viewPagerAdapter
        tab.setupWithViewPager(view_pager_star_wars)
    }
}

package com.shivam.marvelgallery.data.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.application.MarvelGalleryApplication
import com.shivam.marvelgallery.ui.CharactersFragment
import com.shivam.marvelgallery.ui.MoviesFragment

/**
 * Created by stomar on 3/25/18.
 */
class ViewPagerAdapter(var fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    val TAB_NAMES = arrayOf<String>(MarvelGalleryApplication.getApplicationContext().getString(R.string.character), MarvelGalleryApplication.getApplicationContext().getString(R.string.movies))

    var mapFragments = mutableMapOf<String, Fragment>()

    override fun getCount(): Int = TAB_NAMES.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                var characterFragment = CharactersFragment()
                mapFragments.put(TAB_NAMES[0], characterFragment)
                characterFragment
            }
            1 -> {
                var moviesFragment = MoviesFragment()
                mapFragments.put(TAB_NAMES[1], moviesFragment)
                moviesFragment
            }
            else -> throw IllegalArgumentException("Incorrect fragment Index.")
        }
    }

    override fun getPageTitle(position: Int): CharSequence = TAB_NAMES[position]
}
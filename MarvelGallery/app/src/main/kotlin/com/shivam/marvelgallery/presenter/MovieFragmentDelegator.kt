package com.shivam.marvelgallery.presenter

import com.shivam.marvelgallery.data.model.StarWarsFilm
import com.shivam.marvelgallery.data.model.StarWarsFilmsResponse

/**
 * Created by stomar on 3/25/18.
 */
interface MovieFragmentDelegator {
    fun showProgress()

    fun hideProgress()

    fun showError()

    fun showNoInternetDialog()

    fun setMoviesCount(count: Int)

    fun showFilmsData(starWarsFilmsResponse : List<StarWarsFilm>)

}
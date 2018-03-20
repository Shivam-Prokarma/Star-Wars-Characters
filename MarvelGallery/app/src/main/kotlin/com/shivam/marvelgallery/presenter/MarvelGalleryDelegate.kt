package com.shivam.marvelgallery.presenter

/**
 * Created by stomar on 3/18/18.
 */
interface MarvelGalleryDelegate {

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun showNoInternetDialog()

    fun launchCharacterDetailsActivity(characterIndex: Int)

}
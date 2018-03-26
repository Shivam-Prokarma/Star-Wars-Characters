package com.shivam.marvelgallery.presenter

/**
 * Created by stomar on 3/25/18.
 */
interface CharacterFragmentDelegator {
    fun showProgress()

    fun hideProgress()

    fun showError()

    fun showNoInternetDialog()

    fun launchCharacterDetailsActivity(characterIndex: Int)

    fun setCharactersCount(count: Int)

    fun setLoading(value : Boolean)
}
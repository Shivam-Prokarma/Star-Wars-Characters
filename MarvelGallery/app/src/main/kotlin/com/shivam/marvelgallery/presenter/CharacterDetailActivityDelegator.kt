package com.shivam.marvelgallery.presenter

import com.shivam.marvelgallery.data.model.StarWarsCharacterComplete

/**
 * Created by stomar on 3/19/18.
 */
interface CharacterDetailActivityDelegator {

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun showNoInternetDialog()

    fun setCharacterData(characterData : StarWarsCharacterComplete)

}
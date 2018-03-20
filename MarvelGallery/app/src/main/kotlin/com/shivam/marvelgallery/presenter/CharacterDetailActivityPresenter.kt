package com.shivam.marvelgallery.presenter

import com.shivam.marvelgallery.data.networking.StarWarsService
import com.shivam.marvelgallery.data.networking.retrofit
import com.shivam.marvelgallery.utils.CommonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by stomar on 3/19/18.
 */
class CharacterDetailActivityPresenter(var delegator: CharacterDetailActivityDelegator) {

    fun postViewCreated(index: Int) {
        if (!CommonUtils.isInternetPresent()) {
            delegator.showNoInternetDialog()
            return
        }
        retrofit.create(StarWarsService::class.java).getCharactersDetails(index.toString())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { delegator.showProgress() }
                .doOnSuccess {
                    delegator.hideProgress()
                    delegator.setCharacterData(it)
                }
                .doOnError {
                    delegator.hideProgress()
                    delegator.showError()
                }.subscribe()
    }
}
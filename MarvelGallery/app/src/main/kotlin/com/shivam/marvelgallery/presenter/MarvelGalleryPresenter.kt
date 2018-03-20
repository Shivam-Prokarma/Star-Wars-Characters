package com.shivam.marvelgallery.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.net.ConnectivityManager
import com.shivam.marvelgallery.application.MarvelGalleryApplication
import com.shivam.marvelgallery.data.adapters.MarvelCharactersAdapter
import com.shivam.marvelgallery.data.model.StarWarResponse
import com.shivam.marvelgallery.data.model.StarWarsCharacter
import com.shivam.marvelgallery.data.networking.StarWarsService
import com.shivam.marvelgallery.data.networking.retrofit
import com.shivam.marvelgallery.utils.CommonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by stomar on 3/18/18.
 */
class MarvelGalleryPresenter(val marvelGalleryDelegate: MarvelGalleryDelegate) : MarvelCharactersAdapter.CharacterItemClickListener {

    val mStartWarCharsLiveData = MutableLiveData<List<StarWarsCharacter>>()

    fun postViewCreated() {
        if (!CommonUtils.isInternetPresent()) {
            marvelGalleryDelegate.showNoInternetDialog()
            return
        }
        var starWarService: StarWarsService = retrofit.create(StarWarsService::class.java)
        starWarService.getStarWarsCharsData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { disposable -> marvelGalleryDelegate.showProgress() }
                .doOnSuccess { response ->
                    marvelGalleryDelegate.hideProgress()
                    mStartWarCharsLiveData.value = response.result ?: ArrayList<StarWarsCharacter>()
                }
                .doOnError { error ->
                    marvelGalleryDelegate.hideProgress()
                    marvelGalleryDelegate.showError()
                }
                .subscribe()
    }

    override fun onClick(i: Int) {
        marvelGalleryDelegate.launchCharacterDetailsActivity(i)
    }
}
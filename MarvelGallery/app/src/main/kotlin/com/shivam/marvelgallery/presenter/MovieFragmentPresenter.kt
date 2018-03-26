package com.shivam.marvelgallery.presenter

import android.arch.lifecycle.MutableLiveData
import com.shivam.marvelgallery.data.model.StarWarsFilm
import com.shivam.marvelgallery.data.model.StarWarsFilmsResponse
import com.shivam.marvelgallery.data.networking.StarWarsService
import com.shivam.marvelgallery.data.networking.retrofit
import com.shivam.marvelgallery.utils.CommonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by stomar on 3/25/18.
 */
class MovieFragmentPresenter(var delegator: MovieFragmentDelegator) {

    var mMovieLiveData = MutableLiveData<List<StarWarsFilm>>()

    fun fetchMoviesData() {
        if (!CommonUtils.isInternetPresent()) {
            delegator.showNoInternetDialog()
            return
        }
        retrofit.create(StarWarsService::class.java).getStarWarFilms().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    delegator.showProgress()
                }
                .doOnError {
                    delegator.hideProgress()
                    delegator.showError()
                }
                .doOnSuccess {
                    delegator.hideProgress()
                    delegator.setMoviesCount(it.count ?: 0)
                    mMovieLiveData.value = it.results
                }.subscribe()
    }

}
package com.shivam.marvelgallery.presenter

import android.arch.lifecycle.MutableLiveData
import com.shivam.marvelgallery.data.adapters.MarvelCharactersAdapter
import com.shivam.marvelgallery.data.model.StarWarResponse
import com.shivam.marvelgallery.data.networking.StarWarsService
import com.shivam.marvelgallery.data.networking.retrofit
import com.shivam.marvelgallery.utils.CommonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by stomar on 3/18/18.
 */
class MarvelGalleryPresenter(val marvelGalleryDelegate: MarvelGalleryDelegate) {

}
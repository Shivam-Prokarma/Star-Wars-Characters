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
 * Created by stomar on 3/25/18.
 */
class CharactersFragmentPresenter(var mCharacterFragmentDelegator: CharacterFragmentDelegator) : MarvelCharactersAdapter.CharacterItemClickListener {

    val mStartWarCharsLiveData = MutableLiveData<StarWarResponse>()

    fun loadCharactersAtIndex(index: Int) {
        print("----loadCharactersAtIndex: $index")
        if (!CommonUtils.isInternetPresent()) {
            mCharacterFragmentDelegator.showNoInternetDialog()
            return
        }
        var starWarService: StarWarsService = retrofit.create(StarWarsService::class.java)
        starWarService.getStarWarsCharsData(index).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
            mCharacterFragmentDelegator.setLoading(true)
            if (index == 1) {
                mCharacterFragmentDelegator.showProgress()
            }
        }.doOnSuccess {
            mCharacterFragmentDelegator.setLoading(false)
            if (index == 1) {
                mCharacterFragmentDelegator.hideProgress()
                mCharacterFragmentDelegator.setCharactersCount(it.countCharacters ?: 0)
            }
            mStartWarCharsLiveData.value = it
        }.doOnError {
            mCharacterFragmentDelegator.setLoading(false)
            if (index == 1) {
                mCharacterFragmentDelegator.hideProgress()
                mCharacterFragmentDelegator.showError()
            }
        }.subscribe()
    }

    override fun onClick(i: Int) {
        mCharacterFragmentDelegator.launchCharacterDetailsActivity(i)
    }
}
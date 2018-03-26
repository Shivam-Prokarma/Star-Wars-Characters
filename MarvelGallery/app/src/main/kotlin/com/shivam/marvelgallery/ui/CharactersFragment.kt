package com.shivam.marvelgallery.ui

import android.app.AlertDialog
import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.application.getIndexFromUrl
import com.shivam.marvelgallery.data.adapters.MarvelCharactersAdapter
import com.shivam.marvelgallery.data.model.StarWarResponse
import com.shivam.marvelgallery.data.model.StarWarsCharacterComplete
import com.shivam.marvelgallery.presenter.CharacterFragmentDelegator
import com.shivam.marvelgallery.presenter.CharactersFragmentPresenter
import kotlinx.android.synthetic.main.fragment_characters.*


class CharactersFragment : Fragment(), CharacterFragmentDelegator {

    var index: Int? = null

    var isLoading: Boolean? = null

    private lateinit var mCharactersFragmentPresenter: CharactersFragmentPresenter

    private var mMarvelCharacterAdapter: MarvelCharactersAdapter? = null

    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createProgressDialog()
        mCharactersFragmentPresenter = CharactersFragmentPresenter(this)
        setUpRecyclerView()
        mCharactersFragmentPresenter.loadCharactersAtIndex(index ?: 1)
        mCharactersFragmentPresenter.mStartWarCharsLiveData.observe(this, Observer { starWarResponse -> addStarWarsCharacters(starWarResponse!!) })
    }

    private fun setUpRecyclerView() {
        mMarvelCharacterAdapter = MarvelCharactersAdapter(activity, mutableListOf<StarWarsCharacterComplete>(), mCharactersFragmentPresenter)
        recycler_view_marvel.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mMarvelCharacterAdapter
        }
        recycler_view_marvel.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) {
                    var linearLayoutManager: LinearLayoutManager = recycler_view_marvel.layoutManager as LinearLayoutManager
                    var totalCount = linearLayoutManager.childCount
                    var visibleCount = linearLayoutManager.itemCount
                    var visibleIndex = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                    if (!isLoading!! && index!! > 0 && visibleIndex + visibleCount >= totalCount) {
                        mCharactersFragmentPresenter.loadCharactersAtIndex(index!!)
                    }
                }
            }
        })
    }

    override fun showProgress() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    override fun showError() {
        var builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.error_occured)
        builder.setCancelable(false)
        builder.setNeutralButton(R.string.ok, { dialogInterface, i -> dialogInterface.dismiss() })
        builder.create().show()
    }

    fun createProgressDialog() {
        progressDialog = ProgressDialog(activity, R.style.ThemeOverlay_AppCompat_Dialog_Alert_ProgressBar)
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.isIndeterminate = true
    }

    override fun showNoInternetDialog() {
        var builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.no_internet)
        builder.setCancelable(false)
        builder.setNeutralButton(R.string.ok, { dialogInterface, i -> dialogInterface.dismiss() })
        builder.create().show()
    }

    fun addStarWarsCharacters(starWarResponse: StarWarResponse) {
        mMarvelCharacterAdapter?.removeLoader()
        mMarvelCharacterAdapter?.addMoreCharacters(starWarResponse.result!!)
        if (!starWarResponse.nextUrl.isNullOrEmpty()) {
            mMarvelCharacterAdapter?.addLoader()
            Log.i("index", index.toString())
        }
        index = starWarResponse.nextUrl.getIndexFromUrl()
    }

    override fun launchCharacterDetailsActivity(characterIndex: Int) {
        var intent = Intent(activity, CharacterDetailsActivity::class.java).apply {
            putExtra(CharacterDetailsActivity.KEY_CHARACTER_INDEX, characterIndex)
        }
        activity.startActivity(intent)
    }

    override fun setCharactersCount(count: Int) {
        text_view_characters_count.text = String.format(getString(R.string.characters_count), count)
    }

    override fun setLoading(value: Boolean) {
        isLoading = value
    }
}

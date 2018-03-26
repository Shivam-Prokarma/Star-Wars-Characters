package com.shivam.marvelgallery.ui

import android.app.AlertDialog
import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.data.adapters.StarWarFilmsAdapter
import com.shivam.marvelgallery.data.model.StarWarsFilm
import com.shivam.marvelgallery.presenter.MovieFragmentDelegator
import com.shivam.marvelgallery.presenter.MovieFragmentPresenter
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment(), MovieFragmentDelegator {

//    private var isDataLoaded : Boolean = false

    private lateinit var progressDialog: ProgressDialog

    private lateinit var mPresenter: MovieFragmentPresenter

    private lateinit var starWarFilmAdapter : StarWarFilmsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createProgressDialog()
        mPresenter = MovieFragmentPresenter(this)
        mPresenter.mMovieLiveData.observe(this, Observer {
            showFilmsData(it ?: emptyList<StarWarsFilm>())
        })
        mPresenter.fetchMoviesData()
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

    override fun showNoInternetDialog() {
        var builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.no_internet)
        builder.setCancelable(false)
        builder.setNeutralButton(R.string.ok, { dialogInterface, i -> dialogInterface.dismiss() })
        builder.create().show()
    }

    override fun setMoviesCount(count: Int) {
        textview_film_count.text = String.format(getString(R.string.film_count), count)
    }

    override fun showFilmsData(starWarsFilmsResponse: List<StarWarsFilm>) {
        starWarFilmAdapter = StarWarFilmsAdapter(starWarsFilmsResponse)
        recycler_view_films.layoutManager = LinearLayoutManager(activity)
        recycler_view_films.adapter = starWarFilmAdapter
    }

    fun createProgressDialog() {
        progressDialog = ProgressDialog(activity, R.style.ThemeOverlay_AppCompat_Dialog_Alert_ProgressBar)
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.isIndeterminate = true
    }
}

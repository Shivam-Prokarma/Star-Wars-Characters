package com.shivam.marvelgallery.ui

import android.app.AlertDialog
import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.data.adapters.MarvelCharactersAdapter
import com.shivam.marvelgallery.data.model.StarWarsCharacter
import com.shivam.marvelgallery.presenter.MarvelGalleryDelegate
import com.shivam.marvelgallery.presenter.MarvelGalleryPresenter
import kotlinx.android.synthetic.main.activity_marvel_gallery.*

class MarvelGalleryActivity : AppCompatActivity(), MarvelGalleryDelegate {

    private lateinit var mMarvelGalleryPresenter: MarvelGalleryPresenter

    private var mMarcvelCharacterAdapter: MarvelCharactersAdapter? = null

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_gallery)
        setSupportActionBar(toolbar)
        toolbar?.setTitle(R.string.app_name)
        mMarvelGalleryPresenter = MarvelGalleryPresenter(this)
        createProgressDialog()
        setUpRecyclerView()
        mMarvelGalleryPresenter.postViewCreated()
        mMarvelGalleryPresenter.mStartWarCharsLiveData.observe(this, Observer { characterList -> addStarWarsAdapter(characterList!!) })
    }

    private fun setUpRecyclerView() {
        recycler_view_marvel.apply {
            layoutManager = LinearLayoutManager(this@MarvelGalleryActivity)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_marvel_gallery, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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
        var builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.error_occured)
        builder.setCancelable(false)
        builder.setNeutralButton(R.string.ok, { dialogInterface, i -> dialogInterface.dismiss() })
        builder.create().show()
    }

    fun createProgressDialog() {
        progressDialog = ProgressDialog(this, R.style.ThemeOverlay_AppCompat_Dialog_Alert_ProgressBar)
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.isIndeterminate = true
    }

    override fun showNoInternetDialog() {
        var builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.no_internet)
        builder.setCancelable(false)
        builder.setNeutralButton(R.string.ok, { dialogInterface, i -> dialogInterface.dismiss() })
        builder.create().show()
    }

    fun addStarWarsAdapter(characterList: List<StarWarsCharacter>) {
        mMarcvelCharacterAdapter = MarvelCharactersAdapter(this, characterList, mMarvelGalleryPresenter)
        recycler_view_marvel.adapter = mMarcvelCharacterAdapter
        mMarcvelCharacterAdapter?.notifyDataSetChanged()
    }

    override fun launchCharacterDetailsActivity(characterIndex: Int) {
        var intent = Intent(this, CharacterDetailsActivity::class.java).apply {
            putExtra(CharacterDetailsActivity.KEY_CHARACTER_INDEX, characterIndex)
        }
        startActivity(intent)
    }
}

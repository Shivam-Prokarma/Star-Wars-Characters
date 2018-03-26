package com.shivam.marvelgallery.ui

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.data.model.StarWarsCharacterComplete
import com.shivam.marvelgallery.presenter.CharacterDetailActivityDelegator
import com.shivam.marvelgallery.presenter.CharacterDetailActivityPresenter

import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetailsActivity : AppCompatActivity(), CharacterDetailActivityDelegator {

    companion object {
        const val KEY_CHARACTER_INDEX = "Character_Index"
    }

    private lateinit var progressDialog: ProgressDialog
    private lateinit var presenter: CharacterDetailActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = CharacterDetailActivityPresenter(this);
        createProgressDialog()
        presenter.postViewCreated(intent?.getIntExtra(KEY_CHARACTER_INDEX, 1) ?: 1)
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
        var builder = AlertDialog.Builder(this).apply {
            setMessage(R.string.error_occured)
            setCancelable(false)
            setNeutralButton(R.string.ok, { dialogInterface, i -> dialogInterface.dismiss() })
        }
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

    override fun setCharacterData(characterData: StarWarsCharacterComplete) {
        name.text = String.format(resources.getString(R.string.name), characterData.name)
        height.text = String.format(resources.getString(R.string.height), characterData.height)
        mass.text = String.format(resources.getString(R.string.mass), characterData.mass)
        hair_color.text = String.format(resources.getString(R.string.hair_color), characterData.hairColor)
        skin_color.text = String.format(resources.getString(R.string.skin_color), characterData.skinColor)
        eye_color.text = String.format(resources.getString(R.string.eye_color), characterData.eyeColor)
        birth_year.text = String.format(resources.getString(R.string.birth_year), characterData.birthYear)
        gender.text = String.format(resources.getString(R.string.gender), characterData.gender)
    }
}

package com.shivam.marvelgallery.application

import android.app.Application
import android.content.Context

/**
 * Created by stomar on 3/18/18.
 */
class MarvelGalleryApplication() : Application() {

    companion object {
        private lateinit var APPLICATION: Context
        fun getApplicationContext() = APPLICATION
    }

    override fun onCreate() {
        super.onCreate()
        APPLICATION = applicationContext
    }
}
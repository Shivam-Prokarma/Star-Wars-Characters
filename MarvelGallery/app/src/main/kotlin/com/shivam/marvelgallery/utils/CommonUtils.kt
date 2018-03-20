package com.shivam.marvelgallery.utils

import android.content.Context
import android.net.ConnectivityManager
import com.shivam.marvelgallery.application.MarvelGalleryApplication

/**
 * Created by stomar on 3/19/18.
 */
class CommonUtils {

    companion object {
        fun isInternetPresent(): Boolean {
            var connectivityManager: ConnectivityManager? = MarvelGalleryApplication.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
        }
    }

}
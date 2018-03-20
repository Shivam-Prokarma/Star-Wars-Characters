package com.shivam.marvelgallery.data.networking

import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.application.MarvelGalleryApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by stomar on 3/18/18.
 */

val retrofit by lazy { createRetrofit() }

fun createOkHttpClient(): OkHttpClient {
    var builder: OkHttpClient.Builder = OkHttpClient.Builder().apply {
        connectTimeout(MarvelGalleryApplication.getApplicationContext().resources.getInteger(R.integer.api_connection_timeout).toLong(), TimeUnit.SECONDS)
        readTimeout(MarvelGalleryApplication.getApplicationContext().resources.getInteger(R.integer.api_read_timeout).toLong() , TimeUnit.SECONDS)
    }
    return builder.build()
}

fun createRetrofit(): Retrofit {
    var builder: Retrofit.Builder = Retrofit.Builder().apply {
        baseUrl(MarvelGalleryApplication.getApplicationContext().getString(R.string.start_wars_base_url))
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        client(createOkHttpClient())
    }
    return builder.build()
}

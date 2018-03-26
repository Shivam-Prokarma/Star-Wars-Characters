package com.shivam.marvelgallery.data.networking

import com.shivam.marvelgallery.data.model.StarWarResponse
import com.shivam.marvelgallery.data.model.StarWarsCharacterComplete
import com.shivam.marvelgallery.data.model.StarWarsFilmsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by stomar on 3/19/18.
 */
interface StarWarsService {

    @GET("people")
    fun getStarWarsCharsData(@Query("page") index: Int): Single<StarWarResponse>

    @GET("people/{index}")
    fun getCharactersDetails(@Path("index") index: String): Single<StarWarsCharacterComplete>

    @GET("films")
    fun getStarWarFilms(): Single<StarWarsFilmsResponse>

}
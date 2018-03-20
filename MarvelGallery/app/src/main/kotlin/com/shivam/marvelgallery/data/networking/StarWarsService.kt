package com.shivam.marvelgallery.data.networking

import com.shivam.marvelgallery.data.model.StarWarResponse
import com.shivam.marvelgallery.data.model.StarWarsCharacterComplete
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by stomar on 3/19/18.
 */
interface StarWarsService {

    @GET("people")
    fun getStarWarsCharsData(): Single<StarWarResponse>

    @GET("people/{index}")
    fun getCharactersDetails(@Path("index") index: String): Single<StarWarsCharacterComplete>
}
package com.shivam.marvelgallery.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by stomar on 3/25/18.
 */
class StarWarsFilmsResponse {
    @Expose
    @SerializedName("count")
    var count: Int? = null
    @Expose
    @SerializedName("results")
    var results: List<StarWarsFilm>? = null
}
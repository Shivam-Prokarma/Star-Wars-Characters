package com.shivam.marvelgallery.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by stomar on 3/19/18.
 */
class StarWarResponse() {

    @Expose
    @SerializedName(value = "count")
    var countCharacters: Int? = null

    @Expose
    @SerializedName(value = "next")
    var nextUrl: String? = null

    @Expose
    @SerializedName(value = "results")
    var result: List<StarWarsCharacterComplete>? = null

}
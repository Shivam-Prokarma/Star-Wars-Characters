package com.shivam.marvelgallery.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by stomar on 3/19/18.
 */
class StarWarsCharacterComplete : StarWarsCharacter() {
    @Expose
    @SerializedName(value = "mass")
    var mass: String? = null

    @Expose
    @SerializedName(value = "hair_color")
    var hairColor: String? = null

    @Expose
    @SerializedName(value = "skin_color")
    var skinColor: String? = null

    @Expose
    @SerializedName(value = "eye_color")
    var eyeColor: String? = null

    @Expose
    @SerializedName(value = "birth_year")
    var birthYear: String? = null

    @Expose
    @SerializedName(value = "gender")
    var gender: String? = null
}
package com.shivam.marvelgallery.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by stomar on 3/18/18.
 */
open class StarWarsCharacter() : Parcelable {

    @Expose
    @SerializedName(value = "name")
    var name: String? = null

    @Expose
    @SerializedName(value = "height")
    var height: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        height = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(name)
        parcel?.writeString(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StarWarsCharacter> {
        override fun createFromParcel(parcel: Parcel): StarWarsCharacter {
            return StarWarsCharacter(parcel)
        }

        override fun newArray(size: Int): Array<StarWarsCharacter?> {
            return arrayOfNulls(size)
        }
    }
}
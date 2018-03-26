package com.shivam.marvelgallery.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by stomar on 3/25/18.
 */
class StarWarsFilm {
    @Expose
    @SerializedName("title")
    var title: String? = null
    @Expose
    @SerializedName("episode_id")
    var episodeId: Int? = null
    @Expose
    @SerializedName("director")
    var director: String? = null
    @Expose
    @SerializedName("producer")
    var producer: String? = null
    @Expose
    @SerializedName("release_date")
    var releaseDate: String? = null
    @Expose
    @SerializedName("opening_crawl")
    var openingCrawl: String? = null
}
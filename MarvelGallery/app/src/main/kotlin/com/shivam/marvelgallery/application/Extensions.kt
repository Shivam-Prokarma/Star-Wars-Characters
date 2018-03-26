package com.shivam.marvelgallery.application

/**
 * Created by stomar on 3/24/18.
 */

inline fun String?.getIndexFromUrl() : Int = this?.substring(this.indexOf("page=") + 5)?.toInt() ?: -1

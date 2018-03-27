package com.shivam.starwars

import com.shivam.marvelgallery.application.getIndexFromUrl
import com.shivam.marvelgallery.data.networking.baseUrl
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by stomar on 3/26/18.
 */
class ExtensionFunctionsTest {

    var index = "1"
    var completeUrl = "$baseUrl?page=$index"

    @Before
    fun setUp() {
    }

    @Test
    fun testGetIndexFromUrl() {
        Assert.assertEquals(completeUrl.getIndexFromUrl().toString(), index)
    }

    @After
    fun clean() {
    }

}
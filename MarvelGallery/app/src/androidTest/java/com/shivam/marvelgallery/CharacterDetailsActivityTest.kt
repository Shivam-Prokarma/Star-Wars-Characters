package com.shivam.marvelgallery

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.runner.AndroidJUnitRunner
import com.shivam.marvelgallery.data.networking.baseUrl
import com.shivam.marvelgallery.ui.CharacterDetailsActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by stomar on 3/27/18.
 */
@RunWith(AndroidJUnit4::class)
class CharacterDetailsActivityTest : AndroidJUnitRunner() {

    val charaterDetailsResponse : String by lazy {parseJsonFromResource("charater_details_success")}

    public var server: MockWebServer? = null

    @get:Rule
    public var activityTestRule: ActivityTestRule<CharacterDetailsActivity> = ActivityTestRule<CharacterDetailsActivity>(CharacterDetailsActivity::class.java, true, false)

    fun parseJsonFromResource(resourceName: String): String {
        var inputStream = CharacterDetailsActivity::class.java.classLoader.getResourceAsStream(resourceName)
        var byteArray = ByteArray(inputStream.available())
        var len = inputStream.read(byteArray)
        if (len > -1) {
            return String(byteArray)
        }
        return String()
    }

    @Before
    fun setUp() {
        server = MockWebServer()
        server?.start()
        baseUrl = server?.url("/").toString()
    }

    @Test
    fun testRecyclerViewPopulated() {
        server?.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody(charaterDetailsResponse)
        })

        activityTestRule.launchActivity(Intent())
        Thread.sleep(1000)
        onView(ViewMatchers.withId(R.id.name)).check(ViewAssertions.matches(ViewMatchers.withText("Name: Luke Skywalker")))
    }

    @After
    fun clean() {
        server?.shutdown()
    }
}
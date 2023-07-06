package com.project.napptilus.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.project.napptilus.R
import com.project.napptilus.enqueueMany
import com.project.napptilus.fromJson
import com.project.napptilus.rules.MockWebServerRule
import com.project.napptilus.ui.base.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Matchers.allOf

import org.junit.Test

import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeInstrumentedTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()

        mockWebServerRule.server.enqueueMany(
            MockResponse().fromJson("DataWrapperDTO.json")
        )

    }

    @Test
    fun scroll_on_items() {
        Espresso.onView(withId(R.id.rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))
    }

    @Test
    fun gender_is_displayed() {
        Espresso.onView(withId(R.id.rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))

        Espresso.onView(
            allOf(
                withText(R.string.gender),
                allOf(
                    isDescendantOfA(withId(R.id.rv)),
                    isDisplayed()
                )
            )
        )
    }

    @Test
    fun profession_is_displayed() {
        Espresso.onView(withId(R.id.rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        Espresso.onView(
            allOf(
                withText(R.string.profession),
                allOf(
                    isDescendantOfA(withId(R.id.rv)),
                    isDisplayed()
                )
            )
        )
    }
}
package com.jeff.offlineimagesdemo.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff.offlineimagesdemo.data.TestData
import com.jeff.offlineimagesdemo.model.Item
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.core.app.ActivityScenario
import com.jeff.offlineimagesdemo.R
import android.view.View
import androidx.test.espresso.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import android.content.Context


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLoader() {
        val str = getApplicationContext<Context>().getResources().getString(R.string.Loading_data)
        onView(withText(str)).check(matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun getRecyclerView() {
        registerIdlingResource()
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<ItemsAdapter.ArticleViewHolder>(50, clickChildViewWithId(R.id.tvLink)))
        onView(withId(R.id.tvSource)).check(matches(isDisplayed()));
        unregisterIdlingResource()
    }

    private fun clickChildViewWithId(id: Int) = object : ViewAction {

        override fun getConstraints() = null

        override fun getDescription() = "view with id $id"

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(id)
            v.performClick()
        }
    }

    private var mIdlingResource: IdlingResource? = null

    private fun registerIdlingResource() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            mIdlingResource = activity.countingIdlingResource
            IdlingRegistry.getInstance().register(mIdlingResource!!)
        }
    }

    private fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource)
        }
    }
}
package com.yoesuv.androidroom

import android.content.Context
import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.yoesuv.androidroom.menu.task.views.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MyApplicationTest {

    private val delay = 1000L
    private lateinit var context: Context

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun register() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun fullFlowTest() {
        val fab = onView(withId(R.id.fabMain))
        val etTitle = onView(withId(R.id.editTextTaskTitle))
        val etContent = onView(withId(R.id.editTextTaskContent))

        fab.perform(click())
        onView(withText(context.getString(R.string.insert_new_task))).check(matches(isDisplayed()))
        SystemClock.sleep(delay)
        onView(withId(R.id.buttonCancel)).perform(click())
        SystemClock.sleep(delay)

        fab.perform(click())
        onView(withText(context.getString(R.string.insert_new_task))).check(matches(isDisplayed()))
        SystemClock.sleep(delay)
        onView(withId(R.id.buttonApply)).perform(click())
        onView(withText(context.getString(R.string.error_input_title_empty))).check(matches(isDisplayed()))
        SystemClock.sleep(delay)
        onView(withId(R.id.buttonCancel)).perform(click())

        fab.perform(click())
        etTitle.perform(typeText("Shop"))
        etTitle.perform(closeSoftKeyboard())
        onView(withId(R.id.buttonApply)).perform(click())
        onView(withText(context.getString(R.string.error_input_content_empty))).check(matches(isDisplayed()))
        SystemClock.sleep(delay)
        onView(withId(R.id.buttonCancel)).perform(click())
        SystemClock.sleep(delay)

        fab.perform(click())
        etTitle.perform(typeText("Shop"))
        etTitle.perform(closeSoftKeyboard())
        etContent.perform(typeText("Coffee, Sugar"))
        etContent.perform(closeSoftKeyboard())
        onView(withId(R.id.buttonApply)).perform(click())
    }
}

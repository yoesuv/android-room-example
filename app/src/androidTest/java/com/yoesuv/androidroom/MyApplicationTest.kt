package com.yoesuv.androidroom

import android.content.Context
import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.yoesuv.androidroom.menu.task.adapters.TaskViewHolder
import com.yoesuv.androidroom.menu.task.views.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.runners.MethodSorters

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    fun test1Insert() {
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
        SystemClock.sleep(delay)
    }

    @Test
    fun test2Edit() {
        val rv = onView(withId(R.id.recyclerViewMain))
        rv.perform(RecyclerViewActions.scrollToPosition<TaskViewHolder>(0))
        SystemClock.sleep(delay)
        rv.perform(RecyclerViewActions.actionOnItemAtPosition<TaskViewHolder>(0, click()))
        SystemClock.sleep(delay)
    }

    @Test
    fun test3DeleteAll() {
        onView(withId(R.id.actionDeleteAll)).perform(click())
        onView(withText(context.getString(R.string.delete_all_message))).check(matches(isDisplayed()))
        SystemClock.sleep(delay)
        onView(withId(R.id.buttonOk)).perform(click())
        SystemClock.sleep(delay)
        onView(withId(R.id.recyclerViewMain)).check(matches(hasChildCount(0)))
        SystemClock.sleep(delay)
        SystemClock.sleep(delay)
    }
}

package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*

import com.moonpi.swiftnotes.R
import org.hamcrest.CoreMatchers.*

class NoteScreen {
    private val titleTextField = withId(R.id.titleEdit)
    private val bodyTextField = withId(R.id.bodyEdit)
    private val backButton = withClassName(endsWith("ImageButton"))

    private val confirmMessage = withResourceName("message")
    private val confirmYesButton = withResourceName("button1")
    private val confirmNoButton = withResourceName("button2")

    fun tapBackButton() {
        onView(backButton).perform(click())
    }

    fun checkTitleHint() {
        withResourceName("button2")
        onView(allOf(titleTextField, isDisplayed())).check(matches(withHint("Title")))
    }

    fun checkBodyHint() {
        onView(allOf(bodyTextField, isDisplayed())).check(matches(withHint("Note")))
    }

    fun checkConfirmDialog() {
        onView(allOf(confirmMessage, isDisplayed())).check(matches(withText("Save changes?")))
        onView(allOf(confirmYesButton, isDisplayed())).check(matches(withText("YES")))
        onView(allOf(confirmNoButton, isDisplayed())).check(matches(withText("NO")))
    }

    fun tapConfirmNoButton() {
        onView(confirmNoButton).perform(click())
    }
}
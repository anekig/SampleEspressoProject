package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import com.moonpi.swiftnotes.R
import org.hamcrest.CoreMatchers.*

class MainScreen {

    private val newNoteButton = withId(R.id.newNote)

    fun tapCreateNoteButton() {
        onView(newNoteButton).perform(click())
    }

    fun checkScreenIsVisible() {
        onView(allOf(withText("Swiftnotes"))).check(matches(isDisplayed()))
    }

}
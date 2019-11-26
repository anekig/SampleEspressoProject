package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*

class Toolbar {
    private val backButton = withClassName(endsWith("ImageButton"))
    private val menuButton = withContentDescription("Ещё")

    fun tapBackButton() {
        Espresso.onView(backButton).perform(ViewActions.click())
    }

    fun tapMenuButton() {
        Espresso.onView(menuButton).perform(ViewActions.click())
    }
}
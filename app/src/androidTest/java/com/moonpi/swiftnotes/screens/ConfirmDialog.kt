package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*

class ConfirmDialog {
    val message = withResourceName("message")
    val yesButton = withResourceName("button1")
    val noButton = withResourceName("button2")

    fun tapConfirmNoButton() {
        onView(noButton).perform(click())
    }

    fun tapConfirmYesButton() {
        onView(yesButton).perform(click())
    }
}
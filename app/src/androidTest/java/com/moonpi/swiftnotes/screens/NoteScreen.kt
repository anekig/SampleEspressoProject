package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*

import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.models.Note
import org.hamcrest.CoreMatchers.*

class NoteScreen {
    val confirmDialog = ConfirmDialog()
    val toolbar = Toolbar()

    private val titleTextField = withId(R.id.titleEdit)
    private val bodyTextField = withId(R.id.bodyEdit)
    private val backButton = withClassName(endsWith("ImageButton"))
    private val menuButton = withContentDescription("Ещё")

    fun checkTitleHint() {
        onView(allOf(titleTextField, isDisplayed())).check(matches(withHint("Title")))
    }

    fun checkBodyHint() {
        onView(allOf(bodyTextField, isDisplayed())).check(matches(withHint("Note")))
    }

    fun typeTitleAndBody() {
        onView(titleTextField).perform(replaceText(Note.default.title))
        onView(bodyTextField).perform(replaceText(Note.default.body))
    }

    fun checkTitleAndBody() {
        onView(titleTextField).check(matches(withText(Note.default.title)))
        onView(bodyTextField).check(matches(withText(Note.default.body)))
    }

    fun checkConfirmDialog() {
        onView(allOf(confirmDialog.message, isDisplayed())).check(matches(withText("Save changes?")))
        onView(allOf(confirmDialog.yesButton, isDisplayed())).check(matches(withText("YES")))
        onView(allOf(confirmDialog.noButton, isDisplayed())).check(matches(withText("NO")))
    }

    fun checkMenuItems() {
        onView(withText("Note font size")).check(matches(isDisplayed()))
        onView(withText("Hide note body in list")).check(matches(isDisplayed()))
    }
}
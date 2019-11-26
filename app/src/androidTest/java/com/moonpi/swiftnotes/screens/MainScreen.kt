package com.moonpi.swiftnotes.screens

import android.provider.ContactsContract
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.models.Note
import org.hamcrest.CoreMatchers.*

class MainScreen {
    val confirmDialog = ConfirmDialog()
    val toolbar = Toolbar()

    private val newNoteButton = withId(R.id.newNote)
    private val noteCell = withId(R.id.relativeLayout)

    fun tapCreateNoteButton() {
        onView(newNoteButton).perform(click())
    }

    fun checkScreenIsVisible() {
        onView(allOf(withText("Swiftnotes"))).check(matches(isDisplayed()))
    }

    fun checkNewNote() {
        checkScreenIsVisible()
        onView(allOf(withId(R.id.titleView), isDisplayed())).check(matches(withText(Note.default.title)))
        onView(allOf(withId(R.id.bodyView), isDisplayed())).check(matches(withText(Note.default.body)))
    }

    fun checkMenuItems() {
        onView(allOf(withText("Backup notes"), isDisplayed()))
        onView(allOf(withText("Restore notes"), isDisplayed()))
        onView(allOf(withText("Rate app"), isDisplayed()))
    }

    fun longTapNote() {
        onView(noteCell).perform(longClick())
    }

    fun deleteNote() {
        toolbar.tapMenuButton()
        confirmDialog.tapConfirmYesButton()
    }

    fun checkNoteDeletion() {
        onView(noteCell).check(doesNotExist())
    }
}
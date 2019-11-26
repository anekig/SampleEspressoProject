package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import com.moonpi.swiftnotes.screens.NoteScreen
import com.moonpi.swiftnotes.screens.MainScreen
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step

@RunWith(AndroidJUnit4::class)
@DisplayName("Проверка работы создания заметок")
class AppTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    private val mainScreen = MainScreen()
    private val noteScreen = NoteScreen()

    @Test
    @DisplayName("Проверка экрана создания заметки")
    fun checkNewNoteScreen() {
        rule.launchActivity()

        step("Тапаем на кнопку создания новой заметки") {
            mainScreen.tapCreateNoteButton()
            deviceScreenshot("tap_newNoteCreateButton")
        }
        step("Проверяем хинт заголовка") {
            noteScreen.checkTitleHint()
            deviceScreenshot("check_titleHint")
        }
        step("Проверяем хинт текста") {
            noteScreen.checkBodyHint()
            deviceScreenshot("check_textHint")
        }
        step("Нажимаем в тулбаре \"Назад\"") {
            noteScreen.tapBackButton()
            deviceScreenshot("tap_backButton")
        }
        step("Проверяем диалог: текст \"Save changes?\" и кнопки \"No\", \"Yes\".") {
            noteScreen.checkConfirmDialog()
            deviceScreenshot("check_saveDialog")
        }
        step("Нажимаем \"No\"") {
            noteScreen.tapConfirmNoButton()
            deviceScreenshot("tap_noButton")
        }
        step("Проверяем открытие главного экрана") {
            mainScreen.checkScreenIsVisible()
            deviceScreenshot("check_mainScreen")
        }
    }

    @Test
    @DisplayName("Проверка сохранения заметки")
    fun saveNewNote() {
        rule.launchActivity()
        step("Тапаем на кнопку создания новой заметки") {
            onView(withId(R.id.newNote)).perform(click())
            deviceScreenshot("tap_newNoteCreateButton")
        }
        step("Вводим текст в поля \"Title\" и \"Note\"") {
            onView(withId(R.id.titleEdit)).perform(replaceText("Заметка 1"))
            onView(withId(R.id.bodyEdit)).perform(replaceText("Новая"))
            deviceScreenshot("enter_title&body")
        }
        step("Проверяем корректность введенного текста") {
            onView(withId(R.id.titleEdit)).check(matches(withText("Заметка 1")))
            onView(withId(R.id.bodyEdit)).check(matches(withText("Новая")))
            deviceScreenshot("check_title&body")
        }
        step("Нажимаем в тулбаре \"Назад\"") {
            onView(withClassName(endsWith("ImageButton"))).perform(click())
            deviceScreenshot("tap_backButton")
        }
        step("Нажимаем \"Yes\"") {
            onView(withResourceName("button1")).perform(click())
            deviceScreenshot("tap_yesButton")
        }
        step("Проверяем появление новой записи на главном экране") {
            onView(allOf(withText("Swiftnotes"))).check(matches(isDisplayed()))
            onView(allOf(withId(R.id.titleView), isDisplayed())).check(matches(withText("Заметка 1")))
            onView(allOf(withId(R.id.bodyView), isDisplayed())).check(matches(withText("Новая")))
            deviceScreenshot("check_newNote")
        }
    }

    @Test
    @DisplayName("Проверка пунктов меню в тулбаре")
    fun checkMenuPoints() {
        rule.launchActivity()
        step("Тапаем на \"Меню\"") {
            onView(withContentDescription("Ещё")).perform(click())
            deviceScreenshot("tap_menu")
        }
        step("Проверяем отображение пунктов меню: \"Backup notes\", \"Restore notes\", \"Rate app\"") {
            onView(allOf(withText("Backup notes"), isDisplayed()))
            onView(allOf(withText("Restore notes"), isDisplayed()))
            onView(allOf(withText("Rate app"), isDisplayed()))
            deviceScreenshot("check_menuItems")
        }
        step("Тапаем на кнопку создания новой заметки") {
            onView(isRoot()).perform(pressBack())
            onView(withId(R.id.newNote)).perform(click())
            deviceScreenshot("tap_newNoteCreateButton")
        }
        step("Тапаем на \"Меню\"") {
            onView(withContentDescription("Ещё")).perform(click())
            deviceScreenshot("tap_menu")
        }
        step("Проверяем отображение пунктов меню: \"Note font size\", \"Hide note body in list\"") {
            onView(withText("Note font size")).check(matches(isDisplayed()))
            onView(withText("Hide note body in list")).check(matches(isDisplayed()))
            deviceScreenshot("check_menuItems")
        }
    }

    @Test
    @DisplayName("Проверка удаления заметки")
    fun deleteNewNote() {
        rule.launchActivity()
        step("Тапаем на кнопку создания новой заметки") {
            onView(withId(R.id.newNote)).perform(click())
            deviceScreenshot("tap_newNoteCreateButton")
        }
        step("Вводим текст в поля \"Title\" и \"Note\"") {
            onView(withId(R.id.titleEdit)).perform(replaceText("Заметка 1"))
            onView(withId(R.id.bodyEdit)).perform(replaceText("Новая"))
            deviceScreenshot("enter_title&body")
        }
        step("Нажимаем в тулбаре \"Назад\"") {
            onView(withClassName(endsWith("ImageButton"))).perform(click())
            deviceScreenshot("tap_backButton")
        }
        step("Нажимаем \"Yes\"") {
            onView(withResourceName("button1")).perform(click())
            deviceScreenshot("tap_yesButton")
        }
        step("Лонг тап на заметку") {
            onView(withId(R.id.relativeLayout)).perform(longClick())
            deviceScreenshot("longTap_note")
        }
        step("Нажимаем \"Удалить\"") {
            onView(withContentDescription("Ещё")).perform(click())
            onView(withResourceName("button1")).perform(click())
            deviceScreenshot("tap_delete")
        }
        step("Проверяем удаление заметки") {
            onView(withId(R.id.relativeLayout)).check(doesNotExist())
            deviceScreenshot("check_deletion")
        }
    }

}

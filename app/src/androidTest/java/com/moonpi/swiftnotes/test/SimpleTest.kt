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
            noteScreen.toolbar.tapBackButton()
            deviceScreenshot("tap_backButton")
        }
        step("Проверяем диалог: текст \"Save changes?\" и кнопки \"No\", \"Yes\".") {
            noteScreen.checkConfirmDialog()
            deviceScreenshot("check_saveDialog")
        }
        step("Нажимаем \"No\"") {
            noteScreen.confirmDialog.tapConfirmNoButton()
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
            mainScreen.tapCreateNoteButton()
            deviceScreenshot("tap_newNoteCreateButton")
        }
        step("Вводим текст в поля \"Title\" и \"Note\"") {
            noteScreen.typeTitleAndBody()
            deviceScreenshot("enter_title&body")
        }
        step("Проверяем корректность введенного текста") {
            noteScreen.checkTitleAndBody()
            deviceScreenshot("check_title&body")
        }
        step("Нажимаем в тулбаре \"Назад\"") {
            noteScreen.toolbar.tapBackButton()
            deviceScreenshot("tap_backButton")
        }
        step("Нажимаем \"Yes\"") {
            noteScreen.confirmDialog.tapConfirmYesButton()
            deviceScreenshot("tap_yesButton")
        }
        step("Проверяем появление новой записи на главном экране") {
            mainScreen.checkNewNote()
        }
    }

    @Test
    @DisplayName("Проверка пунктов меню в тулбаре")
    fun checkMenuPoints() {
        rule.launchActivity()
        step("Тапаем на \"Меню\"") {
            mainScreen.toolbar.tapMenuButton()
            deviceScreenshot("tap_menu")
        }
        step("Проверяем отображение пунктов меню: \"Backup notes\", \"Restore notes\", \"Rate app\"") {
            mainScreen.checkMenuItems()
            deviceScreenshot("check_menuItems")
        }
        step("Тапаем на кнопку создания новой заметки") {
            onView(isRoot()).perform(pressBack())
            mainScreen.tapCreateNoteButton()
            deviceScreenshot("tap_newNoteCreateButton")
        }
        step("Тапаем на \"Меню\"") {
            mainScreen.toolbar.tapMenuButton()
            deviceScreenshot("tap_menu")
        }
        step("Проверяем отображение пунктов меню: \"Note font size\", \"Hide note body in list\"") {
            noteScreen.checkMenuItems()
            deviceScreenshot("check_menuItems")
        }
    }

    @Test
    @DisplayName("Проверка удаления заметки")
    fun deleteNewNote() {
        rule.launchActivity()
        step("Тапаем на кнопку создания новой заметки") {
            mainScreen.tapCreateNoteButton()
            deviceScreenshot("tap_newNoteCreateButton")
        }
        step("Вводим текст в поля \"Title\" и \"Note\"") {
            noteScreen.typeTitleAndBody()
            deviceScreenshot("enter_title&body")
        }
        step("Нажимаем в тулбаре \"Назад\"") {
            noteScreen.toolbar.tapBackButton()
            deviceScreenshot("tap_backButton")
        }
        step("Нажимаем \"Yes\"") {
            noteScreen.confirmDialog.tapConfirmYesButton()
            deviceScreenshot("tap_yesButton")
        }
        step("Лонг тап на заметку") {
            mainScreen.longTapNote()
            deviceScreenshot("longTap_note")
        }
        step("Нажимаем \"Удалить\"") {
            mainScreen.deleteNote()
            deviceScreenshot("tap_delete")
        }
        step("Проверяем удаление заметки") {
            mainScreen.checkNoteDeletion()
            deviceScreenshot("check_deletion")
        }
    }

}

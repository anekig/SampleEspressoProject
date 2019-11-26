package com.moonpi.swiftnotes.models

class Note(val title: String, val body: String) {

    companion object {
        val default = Note("Заметка 1", "Новая")
    }

}

package com.otsembo.pinit.notes_data.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AppNote(
    var noteId: String? = null,
    var noteTitle: String? = null,
    var description: String? = null,
    var status: NoteStatus = NoteStatus.TODO,
    var user: String,
    var imageUrl: String? = null
)

enum class NoteStatus(status: String) {
    TODO("todo"),
    ONGOING("ongoing"),
    DONE("done")
}

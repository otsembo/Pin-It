package com.otsembo.pinit.notes_data.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AppNote(
    val noteId: String? = null,
    val noteTitle: String? = null,
    val description: String? = null,
    val status: NoteStatus = NoteStatus.TODO,
    val user: String,
    val imageUrl: String? = null
)

enum class NoteStatus(status: String) {
    TODO("todo"),
    ONGOING("ongoing"),
    DONE("done")
}

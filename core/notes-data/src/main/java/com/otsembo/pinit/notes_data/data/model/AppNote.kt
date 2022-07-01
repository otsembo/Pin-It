package com.otsembo.pinit.notes_data.data.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class AppNote(
    var noteId: String? = null,
    var noteTitle: String? = null,
    var description: String? = null,
    var status: NoteStatus = NoteStatus.TODO,
    var user: String? = null,
    var imageUrl: String? = null,
    var dateCreated: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
)

enum class NoteStatus(val status: String) {
    TODO("todo"),
    ONGOING("ongoing"),
    DONE("done")
}

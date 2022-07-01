package com.otsembo.pinit.notes_data.data.repository

import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

interface NotesRepository : KoinComponent {
    val errorMessage: SharedFlow<String>
    suspend fun createNote(appNote: AppNote): String
    suspend fun updateNote(appNote: AppNote): String
    suspend fun deleteNote(noteId: String): String
    fun displayNotes(): StateFlow<AppResource<List<AppNote>>>
}

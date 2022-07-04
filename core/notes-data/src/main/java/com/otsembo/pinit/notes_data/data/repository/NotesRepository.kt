package com.otsembo.pinit.notes_data.data.repository

import android.graphics.Bitmap
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

interface NotesRepository : KoinComponent {
    val errorMessage: SharedFlow<String>
    val notesImageLocation: SharedFlow<String>
    suspend fun createNote(appNote: AppNote): String
    suspend fun updateNote(appNote: AppNote): String
    suspend fun deleteNote(noteId: String): String
    fun displayNotes(): StateFlow<AppResource<List<AppNote>>>
    suspend fun storeImage(imageBitmap: Bitmap)
}

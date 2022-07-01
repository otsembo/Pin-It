package com.otsembo.pinit.notes_data.presentation.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.model.NoteStatus
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class NoteEditVM(private val notesRepository: NotesRepository) : ViewModel() {

    val errorFlow = notesRepository.errorMessage
    val notes = notesRepository.displayNotes()

    private val _notesImageLocation: MutableSharedFlow<String> = MutableSharedFlow()
    val notesImageLocation: SharedFlow<String> = _notesImageLocation

    fun createNote(appNote: AppNote) {
        viewModelScope.launch {
            notesRepository.createNote(appNote)
        }
    }

    fun updateNote(appNote: AppNote) {
        viewModelScope.launch {
            notesRepository.updateNote(appNote)
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            notesRepository.deleteNote(noteId)
        }
    }

    suspend fun uploadImage(bitmap: Bitmap) {
        _notesImageLocation.emit(notesRepository.storeImage(bitmap))
    }

    fun selectStatus(status: String): NoteStatus {
        return when (status.uppercase()) {
            NoteStatus.TODO.status -> NoteStatus.TODO
            NoteStatus.ONGOING.status -> NoteStatus.ONGOING
            NoteStatus.DONE.status -> NoteStatus.DONE
            else -> NoteStatus.DONE
        }
    }
}

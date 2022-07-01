package com.otsembo.pinit.notes_data.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import kotlinx.coroutines.launch

class NoteEditVM(private val notesRepository: NotesRepository) : ViewModel() {

    val errorFlow = notesRepository.errorMessage
    val notes = notesRepository.displayNotes()

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
}

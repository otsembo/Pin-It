package com.otsembo.pinit.notes_data.presentation.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.model.NoteStatus
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteEditVM(private val notesRepository: NotesRepository) : ViewModel() {

    val errorMessage = notesRepository.errorMessage
    val notes = notesRepository.displayNotes()
    val notesImageLocation = notesRepository.notesImageLocation

    val noteData: MutableStateFlow<AppNote> = MutableStateFlow(AppNote())

    val noteStatus: MutableStateFlow<String> = MutableStateFlow("")

    private val _isDialogClose: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDialogClose: StateFlow<Boolean> = _isDialogClose

    private val _isUploadingImage: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingImage: StateFlow<Boolean> = _isUploadingImage

    fun createNote() {
        viewModelScope.launch {
            noteData.value.status = selectStatus(noteStatus.value)
            notesRepository.createNote(noteData.value)
            _isDialogClose.emit(false)
        }
    }

    fun closeDialog() {
        viewModelScope.launch {
            _isDialogClose.emit(true)
        }
    }

    fun resetDialogState() {
        viewModelScope.launch {
            _isDialogClose.emit(false)
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

    fun uploadImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _isUploadingImage.value = true
            notesRepository.storeImage(bitmap)
            _isUploadingImage.value = false
        }
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

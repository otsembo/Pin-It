package com.otsembo.pinit.notes_data.presentation.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.model.NoteStatus
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoteEditVM(private val notesRepository: NotesRepository) : ViewModel() {

    val errorMessage = notesRepository.errorMessage
    private val notesImageLocation = notesRepository.notesImageLocation

    val noteData: MutableStateFlow<AppNote> = MutableStateFlow(AppNote())
    val noteStatus: MutableStateFlow<String> = MutableStateFlow("")

    private val _appState: MutableStateFlow<AppResource<String>> = MutableStateFlow(AppResource.Idle())
    val appState: StateFlow<AppResource<String>> = _appState

    private val _isDialogClose: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDialogClose: StateFlow<Boolean> = _isDialogClose

    private val _isUploadingImage: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingImage: StateFlow<Boolean> = _isUploadingImage

    init {
        listenForErrors()
        listenForImageUpload()
    }

    fun createNote() {
        viewModelScope.launch {
            _appState.emit(AppResource.Loading())
            noteData.value.status = selectStatus(noteStatus.value)
            notesRepository.createNote(noteData.value)
            if (_appState.value !is AppResource.Error) {
                _appState.emit(AppResource.Success(data = NOTES_ADDED_SUCCESS))
            }
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

    private fun listenForErrors() {
        viewModelScope.launch {
            errorMessage.collectLatest {
                _appState.emit(AppResource.Error(message = it))
            }
        }
    }

    private fun listenForImageUpload() {
        viewModelScope.launch {
            notesImageLocation.collectLatest {
                noteData.value.imageUrl = it
            }
        }
    }

    private fun selectStatus(status: String): NoteStatus {
        return when (status.uppercase()) {
            NoteStatus.TODO.status -> NoteStatus.TODO
            NoteStatus.ONGOING.status -> NoteStatus.ONGOING
            NoteStatus.DONE.status -> NoteStatus.DONE
            else -> NoteStatus.DONE
        }
    }

    companion object {
        const val NOTES_ADDED_SUCCESS = "Notes added successfully!!"
    }
}

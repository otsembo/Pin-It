package com.otsembo.pinit.notes.presentation.noteslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesVM(notesRepository: NotesRepository) : ViewModel() {
    private val allNotes = notesRepository.displayNotes()

    init {
        listenForNotes()
    }

    private fun listenForNotes() {
        viewModelScope.launch {
            allNotes.collect {
                when (it) {
                    is AppResource.Loading -> Unit
                    is AppResource.Idle -> Unit
                    is AppResource.Success -> Unit
                    is AppResource.Error -> Unit
                }
            }
        }
    }
}

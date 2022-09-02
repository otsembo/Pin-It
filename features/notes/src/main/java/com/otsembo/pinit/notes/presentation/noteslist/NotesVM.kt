package com.otsembo.pinit.notes.presentation.noteslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import com.otsembo.pinit.notes_data.presentation.adapters.NotesAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesVM(notesRepository: NotesRepository) : ViewModel() {
    private val allNotes = notesRepository.displayNotes()

    private val _latestNotes: MutableStateFlow<List<AppNote>> = MutableStateFlow(emptyList())
    val latestNotes: StateFlow<List<AppNote>> = _latestNotes

    val adapter = NotesAdapter()

    init {
        listenForNotes()
    }

    private fun listenForNotes() {
        viewModelScope.launch {
            allNotes.collect {
                when (it) {
                    is AppResource.Loading -> Unit
                    is AppResource.Idle -> Unit
                    is AppResource.Success -> {
                        it.data?.let { noteList ->
                            _latestNotes.emit(noteList)
                            adapter.submitItems(noteList)
                        }
                    }
                    is AppResource.Error -> Unit
                }
            }
        }
    }
}

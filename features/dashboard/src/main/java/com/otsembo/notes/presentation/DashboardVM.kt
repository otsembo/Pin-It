package com.otsembo.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import com.otsembo.pinit.notes_data.presentation.adapters.NotesAdapter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardVM(private val notesRepository: NotesRepository) : ViewModel() {

    // notes info
    val notes = notesRepository.displayLatestNotes()

    private val _latestNotes: MutableStateFlow<List<AppNote>> = MutableStateFlow(emptyList())
    val latestNotes: StateFlow<List<AppNote>> = _latestNotes

    private val _errorMessage: MutableSharedFlow<String> = MutableSharedFlow()
    val errorMessage: SharedFlow<String> = _errorMessage

    private val _isDashboardEmpty: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isDashboardEmpty: StateFlow<Boolean> = _isDashboardEmpty

    private val _latestNotesState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val latestNotesState: StateFlow<Boolean> = _latestNotesState

    private val _latestReminderState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val latestReminderState: StateFlow<Boolean> = _latestReminderState

    val adapter = NotesAdapter()

    init {
        registerFlowObserver()
    }

    private fun registerFlowObserver() {
        viewModelScope.launch {
            notes.collect {
                when (it) {
                    is AppResource.Loading -> Unit
                    is AppResource.Success ->
                        it.data?.let { appData ->
                            _isDashboardEmpty.emit(appData.isEmpty())
                            _latestNotesState.emit(appData.isEmpty())
                            _latestNotes.emit(appData)
                            adapter.submitItems(appData)
                        }
                    is AppResource.Error ->
                        it.message?.let { message -> _errorMessage.emit(message) }
                    is AppResource.Idle -> Unit
                }
            }
        }
    }
}

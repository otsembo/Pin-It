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

    // reminders and notes status
    private val dashboardStatus = DashboardStatus()

    // notes info
    val notes = notesRepository.displayNotes()

    private val _latestNotes: MutableStateFlow<List<AppNote>> = MutableStateFlow(emptyList())
    val latestNotes: StateFlow<List<AppNote>> = _latestNotes

    private val _errorMessage: MutableSharedFlow<String> = MutableSharedFlow()
    val errorMessage: SharedFlow<String> = _errorMessage

    private val _isDashboardEmpty: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isDashboardEmpty: StateFlow<Boolean> = _isDashboardEmpty

    private val _dashboardStatus: MutableStateFlow<DashboardStatus> = MutableStateFlow(dashboardStatus)
    val xDashboardStatus: StateFlow<DashboardStatus> = _dashboardStatus

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
                            dashboardStatus.isNotesEmpty = appData.isNotEmpty()
                            _dashboardStatus.emit(dashboardStatus)
                            _latestNotes.emit(appData)
                        }
                    is AppResource.Error ->
                        it.message?.let { message -> _errorMessage.emit(message) }
                    is AppResource.Idle -> Unit
                }
            }
        }
    }


    data class DashboardStatus(var isNotesEmpty: Boolean = true, var isRemindersEmpty: Boolean = true)
}

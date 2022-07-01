package com.otsembo.pinit.notes_data.presentation.add

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.model.NoteStatus
import com.otsembo.pinit.notes_data.databinding.FragmentNoteEditBinding
import com.otsembo.pinit.notes_data.presentation.viewmodels.NoteEditVM
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class NotesEditFragment : DialogFragment() {

    private lateinit var binding: FragmentNoteEditBinding
    private val viewModel: NoteEditVM by inject()
    private val appNote: AppNote by lazy {
        AppNote()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNoteEditBinding.inflate(layoutInflater)
        binding.noteData = appNote
        initClicks()
        return AlertDialog.Builder(requireContext(), com.otsembo.pinit.theming.R.style.FullScreenDialog)
            .setView(binding.root)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(com.otsembo.pinit.theming.R.transition.slide_up)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUiData(savedInstanceState)
        lifecycleScope.launchWhenResumed {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                initFlowObservers()
            }
        }
    }

    private fun initClicks() {
        binding.imgClose.setOnClickListener {
            dismiss()
        }
        binding.txtSave.setOnClickListener {
            appNote.status = when (binding.txtNoteStatus.text.toString().uppercase()) {
                NoteStatus.TODO.status -> NoteStatus.TODO
                NoteStatus.ONGOING.status -> NoteStatus.ONGOING
                NoteStatus.DONE.status -> NoteStatus.DONE
                else -> NoteStatus.DONE
            }
            viewModel.createNote(appNote)
            dismiss()
        }
    }

    private suspend fun initFlowObservers() {
        viewModel.errorFlow.collectLatest {
            displayMessage(it)
        }
        viewModel.notes.collect {
            when (it) {
                is AppResource.Idle -> {}
                is AppResource.Error -> {}
                is AppResource.Success -> {}
                is AppResource.Loading -> {}
            }
        }
    }

    private fun displayMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveUiData(outState)
    }

    private fun saveUiData(bundle: Bundle) {
        bundle.putString(TITLE, appNote.noteTitle)
        bundle.putString(DESCRIPTION, appNote.description)
    }

    private fun loadUiData(bundle: Bundle?) {
        bundle?.let {
            appNote.noteTitle = it.getString(TITLE)
            appNote.description = it.getString(DESCRIPTION)
        }
    }

    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "desc"
        const val STATUS = "status"
    }

    data class NoteData(var title: String, var description: String, var status: String = "")
}

package com.otsembo.pinit.notes_data.presentation.add

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.transition.TransitionInflater
import com.otsembo.pinit.notes_data.R
import com.otsembo.pinit.notes_data.databinding.FragmentNoteEditBinding

class NotesEditFragment : DialogFragment() {

    private lateinit var binding: FragmentNoteEditBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNoteEditBinding.inflate(layoutInflater)
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

    private fun initClicks() {
        binding.imgClose.setOnClickListener {
            dismiss()
        }
        binding.txtSave.setOnClickListener {
            dismiss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    companion object {
    }
}

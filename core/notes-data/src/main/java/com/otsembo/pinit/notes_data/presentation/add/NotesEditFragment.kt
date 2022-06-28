package com.otsembo.pinit.notes_data.presentation.add

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.otsembo.pinit.notes_data.R
import com.otsembo.pinit.notes_data.databinding.FragmentNoteEditBinding

class NotesEditFragment : DialogFragment() {

    private lateinit var binding: FragmentNoteEditBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // val dialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_note_edit, null, false)
        binding = FragmentNoteEditBinding.inflate(layoutInflater)
        return AlertDialog.Builder(requireContext(), com.otsembo.pinit.theming.R.style.FullScreenDialog)
            .setView(binding.root)
            .create()
    }
}

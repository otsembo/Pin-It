package com.otsembo.pinit.notes.presentation.noteslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otsembo.pinit.notes.databinding.FragmentNotesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesListBinding
    private val viewModel: NotesVM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

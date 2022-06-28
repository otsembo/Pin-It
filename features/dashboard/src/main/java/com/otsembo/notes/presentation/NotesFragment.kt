package com.otsembo.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.otsembo.notes.R
import com.otsembo.notes.databinding.FragmentDashboardBinding

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        initClicks()
        return binding.root
    }

    private fun initClicks(){
        binding.fabNotes.setOnClickListener {
            it.findNavController().navigate(R.id.dashboardToNotesEdit)
        }
    }

}

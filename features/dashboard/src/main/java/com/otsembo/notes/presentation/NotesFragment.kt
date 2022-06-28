package com.otsembo.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.transition.TransitionInflater
import com.otsembo.notes.R
import com.otsembo.notes.databinding.FragmentDashboardBinding

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val transitionInflater = TransitionInflater.from(requireContext())
        exitTransition = transitionInflater.inflateTransition(com.otsembo.pinit.theming.R.transition.slide_down)
        initClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
    }

    private fun initClicks() {
        binding.fabNotes.setOnClickListener {
            navController.navigate(R.id.dashboardToNotesEdit)
        }
        binding.btnNotes.setOnClickListener {
            navController.navigate(R.id.dashboardToNotesEdit)
        }
    }
}

package com.otsembo.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.otsembo.notes.R
import com.otsembo.notes.databinding.FragmentDashboardBinding
import com.otsembo.pinit.theming.presentation.snackIt
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var navController: NavController
    private val viewModel: DashboardVM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        initClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = binding.root.findNavController()
        // set binding params
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        // listen for data changes
        registerFlows()
    }

    private fun initClicks() {
        binding.fabNotes.setOnClickListener {
            navController.navigate(R.id.dashboardToNotesEdit)
        }
        binding.btnNotes.setOnClickListener {
            navController.navigate(R.id.dashboardToNotesEdit)
        }
    }

    private fun registerFlows() {
        lifecycleScope.launchWhenResumed {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.latestNotes.collect {
                    viewModel.adapter.submitItems(it)
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.errorMessage.collect {
                    binding.root.snackIt(it)
                }
            }
        }
    }
}

package com.otsembo.pinit.authentication.presentation.pages.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.otsembo.pinit.authentication.R
import com.otsembo.pinit.authentication.databinding.FragmentAuthOnboardingBinding
import kotlinx.coroutines.launch

class OnBoarding : Fragment() {
    // binding object
    private lateinit var binding: FragmentAuthOnboardingBinding

    // view model
    private val viewModel: OnBoardingVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // initialize binding
        binding = FragmentAuthOnboardingBinding.inflate(inflater, container, false)
        binding.onBoardingVM = viewModel

        // listeners
        initNavigationObservers()
        initClickListeners()

        return binding.root
    }

    // init navigation listeners
    private fun initNavigationObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.loginNavigation.collect { if (it) navigateToLogin() }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.registerNavigation.collect { if (it) navigateToRegister() }
        }
    }

    // init click listeners
    private fun initClickListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.navigateToLogin(true)
                }
            }
            btnRegister.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.navigateToRegister(true)
                }
            }
        }
    }

    // navigation
    private suspend fun navigateToRegister() {
        binding.root.findNavController().navigate(R.id.action_onBoarding_to_registerFragment)
        viewModel.navigateToRegister(false)
    }

    private suspend fun navigateToLogin() {
        binding.root.findNavController().navigate(R.id.action_onBoarding_to_loginFragment)
        viewModel.navigateToLogin(false)
    }
}

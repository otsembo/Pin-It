package com.otsembo.pinit.authentication.presentation.pages.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.otsembo.pinit.authentication.R
import com.otsembo.pinit.authentication.databinding.FragmentAuthOnboardingBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoarding : Fragment() {
    // binding object
    private lateinit var binding: FragmentAuthOnboardingBinding

    // view model
    private val viewModel: OnBoardingVM by viewModel()

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
            viewModel.navigationTrigger.collectLatest {
                try {
                    navigate(it)
                } catch (e: Exception) {
                    Unit
                }
            }
        }
    }

    // navigate from onboarding
    private fun navigate(actionId: Int) {
        binding.root.findNavController().navigate(actionId)
    }

    // init click listeners
    private fun initClickListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                viewModel.navigate(R.id.action_onBoarding_to_loginFragment)
            }
            btnRegister.setOnClickListener {
                viewModel.navigate(R.id.action_onBoarding_to_registerFragment)
            }
        }
    }
}

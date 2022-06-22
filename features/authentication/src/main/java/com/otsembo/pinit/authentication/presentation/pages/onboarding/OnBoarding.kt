package com.otsembo.pinit.authentication.presentation.pages.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otsembo.pinit.authentication.databinding.FragmentAuthOnboardingBinding

class OnBoarding : Fragment() {
    private lateinit var binding: FragmentAuthOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // initialize binding
        binding = FragmentAuthOnboardingBinding.inflate(inflater, container, false)

        return binding.root
    }
}

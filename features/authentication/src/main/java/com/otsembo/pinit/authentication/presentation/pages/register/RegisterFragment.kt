package com.otsembo.pinit.authentication.presentation.pages.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.otsembo.pinit.authentication.common.AppResource
import com.otsembo.pinit.authentication.databinding.FragmentAuthRegisterBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentAuthRegisterBinding

    private val registerVM: RegisterVM by viewModel()

    private lateinit var userAccountCreate: AccountCreate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthRegisterBinding.inflate(inflater, container, false)

        userAccountCreate = AccountCreate()
        binding.create = userAccountCreate

        initObservers()
        initClickListeners()
        return binding.root
    }

    private fun toggleLoadingBar(isLoading: Boolean = false) {
        binding.pbSignIn.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun initClickListeners() {
        binding.btnSignIn.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                registerVM.createAccount(userAccountCreate)
            }
        }
    }

    private fun initObservers() {

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                registerVM.registrationProcess.collectLatest {
                    when (it) {
                        is AppResource.Idle -> toggleLoadingBar()
                        is AppResource.Success -> {
                            toggleLoadingBar()
                        }
                        is AppResource.Loading -> toggleLoadingBar(isLoading = true)
                        is AppResource.Error -> {
                            toggleLoadingBar()
                        }
                    }
                }
            }
        }
    }
}

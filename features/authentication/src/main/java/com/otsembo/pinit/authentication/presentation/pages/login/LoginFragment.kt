package com.otsembo.pinit.authentication.presentation.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.otsembo.pinit.authentication.common.AppResource
import com.otsembo.pinit.authentication.databinding.FragmentAuthLoginBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentAuthLoginBinding
    private val viewModel: LoginVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthLoginBinding.inflate(inflater, container, false)

        initUIObservers()
        initClickListeners()
        return binding.root
    }

    private fun toggleLoadingBar(isLoading: Boolean = false) {
        binding.pbSignIn.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun initClickListeners() {
        binding.btnSignIn.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                viewModel.logIn(
                    email = binding.edtEmail.text.toString(),
                    password = binding.edtPassword.text.toString()
                )
            }
        }
    }

    private fun initUIObservers() {
        lifecycleScope.launchWhenStarted {

            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loginMessage.collectLatest {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                }
            }

            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loginStatus.collect {
                    when (it) {
                        is AppResource.Idle -> toggleLoadingBar()
                        is AppResource.Error -> toggleLoadingBar()
                        is AppResource.Success -> toggleLoadingBar()
                        is AppResource.Loading -> toggleLoadingBar(isLoading = true)
                    }
                }
            }
        }
    }
}

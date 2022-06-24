package com.otsembo.pinit.authentication.presentation.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otsembo.pinit.authentication.databinding.FragmentAuthLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentAuthLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}

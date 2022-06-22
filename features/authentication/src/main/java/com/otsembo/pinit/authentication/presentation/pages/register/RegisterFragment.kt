package com.otsembo.pinit.authentication.presentation.pages.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otsembo.pinit.authentication.databinding.FragmentAuthRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentAuthRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAuthRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
}

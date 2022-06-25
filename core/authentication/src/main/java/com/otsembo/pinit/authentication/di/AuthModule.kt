package com.otsembo.pinit.authentication.di

import com.otsembo.pinit.authentication.data.repository.AuthRepository
import com.otsembo.pinit.authentication.domain.data.repository.AuthRepoImpl
import com.otsembo.pinit.authentication.presentation.pages.login.LoginVM
import com.otsembo.pinit.authentication.presentation.pages.onboarding.OnBoardingVM
import com.otsembo.pinit.authentication.presentation.pages.register.RegisterVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AuthModule {
    fun dependencies() = module {
        single { AuthRepoImpl() as AuthRepository }

        // view models
        viewModel { RegisterVM(get()) }
        viewModel { LoginVM(get()) }
        viewModel { OnBoardingVM() }
    }
}

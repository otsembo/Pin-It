package com.otsembo.notes.di

import com.otsembo.notes.presentation.DashboardVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    viewModel { DashboardVM(get()) }
}

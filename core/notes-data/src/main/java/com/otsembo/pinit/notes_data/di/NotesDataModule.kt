package com.otsembo.pinit.notes_data.di

import com.otsembo.pinit.notes_data.presentation.viewmodels.NoteEditVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object NotesDataModule {
    fun dependencies() = module {
        viewModel { NoteEditVM() }
    }
}

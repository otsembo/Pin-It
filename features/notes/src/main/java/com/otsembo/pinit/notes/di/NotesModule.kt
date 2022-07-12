package com.otsembo.pinit.notes.di

import com.otsembo.pinit.notes.presentation.noteslist.NotesVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val NotesModule = module {
    viewModel { NotesVM() }
}

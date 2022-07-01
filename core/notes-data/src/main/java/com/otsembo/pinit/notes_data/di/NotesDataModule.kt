package com.otsembo.pinit.notes_data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import com.otsembo.pinit.notes_data.domain.NotesRepoImpl
import com.otsembo.pinit.notes_data.presentation.viewmodels.NoteEditVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

object NotesDataModule {
    fun dependencies() = module {

        factory { Firebase.firestore as FirebaseFirestore }

        single { NotesRepoImpl(coroutineScope = get(qualifier("mainScope")), fireStore = get()) as NotesRepository }

        viewModel { NoteEditVM(get()) }
    }
}

package com.otsembo.pinit.di

import com.otsembo.pinit.authentication.di.AuthModule
import com.otsembo.pinit.notes_data.di.NotesDataModule
import org.koin.core.module.Module

object AppModules {
    fun appDependencies(): List<Module> {
        return listOf(AuthModule.dependencies(), NotesDataModule.dependencies())
    }
}

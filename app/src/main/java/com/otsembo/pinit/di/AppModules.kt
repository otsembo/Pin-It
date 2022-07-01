package com.otsembo.pinit.di

import com.otsembo.pinit.authentication.di.AuthModule
import com.otsembo.pinit.notes_data.di.NotesDataModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.dsl.single

object AppModules {
    fun appDependencies(): List<Module> {
        return listOf(AuthModule.dependencies(), NotesDataModule.dependencies(), globalDependencies())
    }
    private fun globalDependencies() = module {
        single(named("ioScope")) { CoroutineScope(Dispatchers.IO) }
        single(named("mainScope")) { CoroutineScope(Dispatchers.Main) }
        single(named("defaultScope")) { CoroutineScope(Dispatchers.Default) }
    }
}

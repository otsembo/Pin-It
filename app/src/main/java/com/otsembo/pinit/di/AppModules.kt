package com.otsembo.pinit.di

import com.otsembo.pinit.authentication.di.AuthModule
import org.koin.core.module.Module

object AppModules {
    fun appDependencies(): List<Module> {
        return listOf(AuthModule.dependencies())
    }
}

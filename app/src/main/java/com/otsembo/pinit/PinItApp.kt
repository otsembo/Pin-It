package com.otsembo.pinit

import android.app.Application
import com.otsembo.pinit.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PinItApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PinItApp)
            modules(AppModules.appDependencies())
        }
    }
}

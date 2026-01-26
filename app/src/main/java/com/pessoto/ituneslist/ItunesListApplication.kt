package com.pessoto.ituneslist

import android.app.Application
import com.pessoto.ituneslist.core.di.ModuleInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ItunesListApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ItunesListApplication)
            modules(ModuleInitializer.modules.toList())
        }
    }
}

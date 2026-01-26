package com.pessoto.ituneslist.core.di

import android.content.Context
import androidx.startup.Initializer
import org.koin.core.module.Module

/**
 * Abstract class that provides a mechanism to initialize Koin modules using AndroidX Startup.
 *
 * This class implements the `Initializer` interface from AndroidX Startup and the `KoinModule` interface.
 * It adds the provided modules to the `ModuleInitializer` during the initialization process.
 */
abstract class ModuleProvider : Initializer<List<Module>>, KoinModule {

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    override fun create(context: Context): List<Module> {
        ModuleInitializer.add(modules)
        return modules
    }
}

package com.pessoto.ituneslist.core.di

import org.koin.core.module.Module

/**
 * Object responsible for managing the initialization of Koin modules in the project.
 * This object maintains a mutable set of Koin modules and provides a function to add new modules to this set.
 */
object ModuleInitializer {

    val modules = mutableSetOf<Module>()

    fun add(modules: List<Module>) {
        ModuleInitializer.modules.addAll(modules)
    }
}

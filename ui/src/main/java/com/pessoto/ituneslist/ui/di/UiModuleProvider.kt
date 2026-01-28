package com.pessoto.ituneslist.ui.di

import com.pessoto.ituneslist.core.di.ModuleProvider
import org.koin.core.module.Module

internal class UiModuleProvider: ModuleProvider() {

    override val modules: List<Module>
        get() = listOf(uiModule)
}

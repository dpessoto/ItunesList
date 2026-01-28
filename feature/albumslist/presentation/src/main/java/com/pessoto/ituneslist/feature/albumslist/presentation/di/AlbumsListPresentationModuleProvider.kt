package com.pessoto.ituneslist.feature.albumslist.presentation.di

import com.pessoto.ituneslist.core.di.ModuleProvider
import org.koin.core.module.Module

class AlbumsListPresentationModuleProvider : ModuleProvider() {

    override val modules: List<Module>
        get() = listOf(albumsListPresentationModule)
}

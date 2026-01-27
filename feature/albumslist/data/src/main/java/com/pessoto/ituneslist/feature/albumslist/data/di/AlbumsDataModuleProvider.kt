package com.pessoto.ituneslist.feature.albumslist.data.di

import com.pessoto.ituneslist.core.di.ModuleProvider
import org.koin.core.module.Module

internal class AlbumsDataModuleProvider : ModuleProvider() {

    override val modules: List<Module>
        get() = listOf(albumsDataModule)
}

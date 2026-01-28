package com.pessoto.ituneslist.feature.albumslist.domain.di

import com.pessoto.ituneslist.core.di.ModuleProvider
import org.koin.core.module.Module

internal class AlbumsListDomainModuleProvider : ModuleProvider() {

    override val modules: List<Module>
        get() = listOf(albumsListDomainModule)
}

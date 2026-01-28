package com.pessoto.ituneslist.feature.albumslist.presentation.di

import com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel.AlbumsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val albumsListPresentationModule = module {
    viewModel { AlbumsListViewModel(get()) }
}

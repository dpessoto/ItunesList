package com.pessoto.ituneslist.feature.albumdetail.presentation.di

import com.pessoto.ituneslist.feature.albumdetail.presentation.viewmodel.AlbumDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val albumDetailPresentationModule = module {
    viewModel { parameters ->
        AlbumDetailViewModel(parameters.get())
    }
}

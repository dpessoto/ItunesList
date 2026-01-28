package com.pessoto.ituneslist.feature.albumslist.domain.di

import com.pessoto.ituneslist.feature.albumslist.domain.usecase.FetchAlbumsUseCase
import com.pessoto.ituneslist.feature.albumslist.domain.usecase.FetchAlbumsUseCaseImpl
import org.koin.dsl.module

internal val albumsListDomainModule = module {
    single<FetchAlbumsUseCase> { FetchAlbumsUseCaseImpl(get()) }
}

package com.pessoto.ituneslist.feature.albumslist.data.di

import com.pessoto.ituneslist.feature.albumslist.data.mapper.AlbumMapper
import com.pessoto.ituneslist.feature.albumslist.data.repository.AlbumsRepositoryImpl
import com.pessoto.ituneslist.feature.albumslist.data.service.AlbumsService
import com.pessoto.ituneslist.feature.albumslist.data.source.remote.AlbumsRemoteDataSource
import com.pessoto.ituneslist.feature.albumslist.data.source.remote.AlbumsRemoteDataSourceImpl
import com.pessoto.ituneslist.feature.albumslist.domain.repository.AlbumsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val albumsDataModule = module {
    single { get<Retrofit>().create(AlbumsService::class.java) }
    single<AlbumsRemoteDataSource> { AlbumsRemoteDataSourceImpl(get()) }

    single<AlbumsRepository> { AlbumsRepositoryImpl(get(), AlbumMapper()) }
}

package com.pessoto.ituneslist.feature.albumslist.data.repository

import com.pessoto.ituneslist.core.data.repository.runFlow
import com.pessoto.ituneslist.feature.albumslist.data.mapper.AlbumMapper
import com.pessoto.ituneslist.feature.albumslist.data.source.remote.AlbumsRemoteDataSource
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import com.pessoto.ituneslist.feature.albumslist.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow

internal class AlbumsRepositoryImpl(
    private val remoteDataSource: AlbumsRemoteDataSource,
    private val mapper: AlbumMapper,
) : AlbumsRepository {

    override fun fetchAlbums(limit: Int): Flow<List<Album>> = runFlow(
        map = { dto -> dto.feed.entries.map { mapper.map(it) } },
        invoke = { remoteDataSource.fetchAlbums(limit) }
    )
}

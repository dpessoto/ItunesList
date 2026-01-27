package com.pessoto.ituneslist.feature.albumslist.domain.usecase

import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import com.pessoto.ituneslist.feature.albumslist.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow

internal class FetchAlbumsUseCaseImpl(
    private val repository: AlbumsRepository
) : FetchAlbumsUseCase {

    override fun invoke(limit: Int): Flow<List<Album>> {
        return repository.fetchAlbums(limit)
    }
}

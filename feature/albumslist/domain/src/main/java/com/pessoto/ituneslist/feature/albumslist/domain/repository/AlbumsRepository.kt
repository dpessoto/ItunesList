package com.pessoto.ituneslist.feature.albumslist.domain.repository

import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {

    fun fetchAlbums(limit: Int): Flow<List<Album>>
}

package com.pessoto.ituneslist.feature.albumslist.data.source.remote

import com.pessoto.ituneslist.core.data.source.remote.DataResult
import com.pessoto.ituneslist.core.data.source.remote.safeApiCall
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedResponseDto
import com.pessoto.ituneslist.feature.albumslist.data.service.AlbumsService

internal class AlbumsRemoteDataSourceImpl(
    private val service: AlbumsService,
) : AlbumsRemoteDataSource {

    override suspend fun fetchAlbums(limit: Int): DataResult<FeedResponseDto> {
        return safeApiCall { service.fetchAlbums(limit) }
    }
}

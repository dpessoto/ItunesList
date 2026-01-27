package com.pessoto.ituneslist.feature.albumslist.data.source.remote

import com.pessoto.ituneslist.core.data.source.remote.DataResult
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedResponseDto

internal interface AlbumsRemoteDataSource {

    suspend fun fetchAlbums(limit: Int): DataResult<FeedResponseDto>
}

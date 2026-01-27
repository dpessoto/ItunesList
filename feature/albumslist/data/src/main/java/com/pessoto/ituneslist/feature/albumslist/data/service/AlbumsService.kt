package com.pessoto.ituneslist.feature.albumslist.data.service

import com.pessoto.ituneslist.feature.albumslist.data.model.FeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface AlbumsService {

    @GET("us/rss/topalbums/limit={limit}/json")
    suspend fun fetchAlbums(@Path("limit") limit: Int): FeedResponseDto
}

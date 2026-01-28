package com.pessoto.ituneslist.navigation.route

import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import kotlinx.serialization.Serializable

@Serializable
sealed class ItunesListScreen {

    @Serializable
    object AlbumsList : ItunesListScreen()

    @Serializable
    data class AlbumDetail(
        val id: String,
        val albumName: String,
        val artist: String,
        val image: String,
        val price: String,
        val releaseDate: String,
        val genre: String
    ) : ItunesListScreen()
}

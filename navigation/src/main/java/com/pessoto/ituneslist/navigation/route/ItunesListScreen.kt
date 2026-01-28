package com.pessoto.ituneslist.navigation.route

import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import kotlinx.serialization.Serializable

@Serializable
sealed class ItunesListScreen {

    @Serializable
    object AlbumsList : ItunesListScreen()

    @Serializable
    data class AlbumDetails(val album: Album) : ItunesListScreen()
}

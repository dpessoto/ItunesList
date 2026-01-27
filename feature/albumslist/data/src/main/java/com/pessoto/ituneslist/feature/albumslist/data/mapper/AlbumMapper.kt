package com.pessoto.ituneslist.feature.albumslist.data.mapper

import com.pessoto.ituneslist.core.data.mapper.Mapper
import com.pessoto.ituneslist.feature.albumslist.data.model.AlbumEntryDto
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album

internal class AlbumMapper : Mapper<AlbumEntryDto, Album> {

    override fun map(source: AlbumEntryDto): Album = with(source) {
        return Album(
            id = id.attributes.value,
            name = name.value,
            artist = artist.value,
            imageUrl = images.firstOrNull()?.url.orEmpty()
        )
    }
}

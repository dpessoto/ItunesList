package com.pessoto.ituneslist.feature.albumslist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumEntryDto(
    @SerialName("id") val id: IdDto,
    @SerialName("im:name") val name: LabelDto,
    @SerialName("im:artist") val artist: LabelDto,
    @SerialName("im:image") val images: List<ImageDto>,
)

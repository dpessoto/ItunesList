package com.pessoto.ituneslist.feature.albumslist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedDto(
    @SerialName("entry") val entries: List<AlbumEntryDto>,
)

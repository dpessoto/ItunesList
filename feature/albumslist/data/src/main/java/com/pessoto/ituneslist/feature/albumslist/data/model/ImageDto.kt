package com.pessoto.ituneslist.feature.albumslist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @SerialName("label") val url: String,
)

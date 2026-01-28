package com.pessoto.ituneslist.feature.albumslist.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: String,
    val albumName: String,
    val artist: String,
    val imageUrl: String
)
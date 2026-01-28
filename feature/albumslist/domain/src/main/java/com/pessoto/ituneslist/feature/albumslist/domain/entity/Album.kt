package com.pessoto.ituneslist.feature.albumslist.domain.entity

data class Album(
    val id: String,
    val albumName: String,
    val artist: String,
    val images: List<String>,
    val price: String,
    val releaseDate: String,
    val genre: String
)

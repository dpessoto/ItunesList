package com.pessoto.ituneslist.feature.albumslist.data.mapper

import com.pessoto.ituneslist.feature.albumslist.data.model.AlbumEntryDto
import com.pessoto.ituneslist.feature.albumslist.data.model.CategoryAttributesDto
import com.pessoto.ituneslist.feature.albumslist.data.model.CategoryDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdAttributesDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdDto
import com.pessoto.ituneslist.feature.albumslist.data.model.ImageDto
import com.pessoto.ituneslist.feature.albumslist.data.model.LabelDto
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AlbumMapperTest {

    private val mapper = AlbumMapper()

    @Test
    fun `map should convert AlbumEntryDto to Album correctly`() {
        val dtos = listOf(
            AlbumEntryDto(
                id = IdDto(
                    attributes = IdAttributesDto(value = "1")
                ),
                name = LabelDto(value = "Album 1"),
                artist = LabelDto(value = "Artist 1"),
                images = listOf(ImageDto(url = "https://image1.url")),
                price = LabelDto("$9.99"),
                releaseDate = LabelDto("2026-01-23T00:00:00-07:00"),
                category = CategoryDto(CategoryAttributesDto("Heavy Metal")),
            ),
            AlbumEntryDto(
                id = IdDto(
                    attributes = IdAttributesDto(value = "2")
                ),
                name = LabelDto(value = "Album 2"),
                artist = LabelDto(value = "Artist 2"),
                images = listOf(ImageDto(url = "https://image2.url")),
                price = LabelDto("$9.99"),
                releaseDate = LabelDto("2026-01-23T00:00:00-07:00"),
                category = CategoryDto(CategoryAttributesDto("Heavy Metal")),
            )
        )

        val expected = listOf(
            Album(
                id = "1",
                albumName = "Album 1",
                artist = "Artist 1",
                images = listOf("https://image1.url"),
                price = "$9.99",
                releaseDate = "Jan 23, 2026",
                genre = "Heavy Metal"
            ),
            Album(
                id = "2",
                albumName = "Album 2",
                artist = "Artist 2",
                images = listOf("https://image2.url"),
                price = "$9.99",
                releaseDate = "Jan 23, 2026",
                genre = "Heavy Metal"
            )
        )

        val result = dtos.map { mapper.map(it) }

        assertEquals(expected, result)
    }

    @Test
    fun `map should set imageUrl as empty when images list is empty`() {
        val dto = AlbumEntryDto(
            id = IdDto(
                attributes = IdAttributesDto(value = "3")
            ),
            name = LabelDto(value = "Album 3"),
            artist = LabelDto(value = "Artist 3"),
            images = emptyList(),
            price = LabelDto("$9.99"),
            releaseDate = LabelDto("2026-01-23T00:00:00-07:00"),
            category = CategoryDto(CategoryAttributesDto("Heavy Metal")),
        )

        val expected = Album(
            id = "3",
            albumName = "Album 3",
            artist = "Artist 3",
            images = emptyList(),
            price = "$9.99",
            releaseDate = "Jan 23, 2026",
            genre = "Heavy Metal"
        )

        val result = mapper.map(dto)

        assertEquals(expected, result)
    }
}

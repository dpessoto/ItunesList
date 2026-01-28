package com.pessoto.ituneslist.feature.albumslist.data.repository

import com.pessoto.ituneslist.core.data.source.remote.DataResult
import com.pessoto.ituneslist.feature.albumslist.data.mapper.AlbumMapper
import com.pessoto.ituneslist.feature.albumslist.data.model.AlbumEntryDto
import com.pessoto.ituneslist.feature.albumslist.data.model.CategoryAttributesDto
import com.pessoto.ituneslist.feature.albumslist.data.model.CategoryDto
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedDto
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedResponseDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdAttributesDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdDto
import com.pessoto.ituneslist.feature.albumslist.data.model.ImageDto
import com.pessoto.ituneslist.feature.albumslist.data.model.LabelDto
import com.pessoto.ituneslist.feature.albumslist.data.source.remote.AlbumsRemoteDataSource
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

internal class AlbumsRepositoryImplTest {

    @RelaxedMockK
    private lateinit var remoteDataSource: AlbumsRemoteDataSource

    @RelaxedMockK
    private lateinit var mapper: AlbumMapper
    private lateinit var repository: AlbumsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = AlbumsRepositoryImpl(remoteDataSource, mapper)
    }

    @Test
    fun `Given limit When fetchAlbums Then returns mapped list`() = runBlocking {
        val limit = 10
        val dto = FeedResponseDto(
            feed = FeedDto(
                entries = listOf(
                    AlbumEntryDto(
                        id = IdDto(IdAttributesDto("1")),
                        name = LabelDto("Album 1"),
                        artist = LabelDto("Artist 1"),
                        images = listOf(ImageDto("https://image1.url")),
                        price = LabelDto("$9.99"),
                        releaseDate = LabelDto("2026-01-23T00:00:00-07:00"),
                        category = CategoryDto(CategoryAttributesDto("Heavy Metal")),
                        )
                )
            )
        )
        val mapped = Album(
            id = "1",
            albumName = "Album 1",
            artist = "Artist 1",
            images = listOf("https://image1.url"),
            price = "$9.99",
            releaseDate = "23 Jan 2026",
            genre = "Heavy Metal"
        )
        coEvery { remoteDataSource.fetchAlbums(limit) } returns DataResult.Success(dto)
        every { mapper.map(any()) } returns mapped

        val result = repository.fetchAlbums(limit).first()

        assertEquals(listOf(mapped), result)
    }

    @Test
    fun `Given limit When fetchAlbums throws exception Then error is propagated`() {
        runBlocking {
            val limit = 10
            coEvery { remoteDataSource.fetchAlbums(limit) } throws RuntimeException("Network error")

            assertThrows(RuntimeException::class.java) {
                runBlocking {
                    repository.fetchAlbums(limit).first()
                }
            }
        }
    }
}

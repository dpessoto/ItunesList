package com.pessoto.ituneslist.feature.albumslist.data.source.remote

import com.pessoto.ituneslist.core.data.source.remote.DataResult
import com.pessoto.ituneslist.feature.albumslist.data.model.AlbumEntryDto
import com.pessoto.ituneslist.feature.albumslist.data.model.CategoryAttributesDto
import com.pessoto.ituneslist.feature.albumslist.data.model.CategoryDto
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedDto
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedResponseDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdAttributesDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdDto
import com.pessoto.ituneslist.feature.albumslist.data.model.ImageDto
import com.pessoto.ituneslist.feature.albumslist.data.model.LabelDto
import com.pessoto.ituneslist.feature.albumslist.data.service.AlbumsService
import io.mockk.coEvery
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class AlbumsRemoteDataSourceImplTest {

    @RelaxedMockK
    private lateinit var service: AlbumsService

    private lateinit var dataSource: AlbumsRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = AlbumsRemoteDataSourceImpl(service)
    }

    @Test
    fun `Given limit When fetchAlbums Then returns expected DTO wrapped in Success`() = runBlocking {
        val limit = 10
        val expectedDto = FeedResponseDto(
            feed = FeedDto(
                entries = listOf(
                    AlbumEntryDto(
                        id = IdDto(
                            attributes = IdAttributesDto(
                                value = "123"
                            )
                        ),
                        name = LabelDto(value = "Test Album"),
                        artist = LabelDto(value = "Test Artist"),
                        images = listOf(
                            ImageDto(url = "https://example.com/image1.jpg"),
                            ImageDto(url = "https://example.com/image2.jpg")
                        ),
                        price = LabelDto("$9.99"),
                        releaseDate = LabelDto("2026-01-23T00:00:00-07:00"),
                        category = CategoryDto(CategoryAttributesDto("Heavy Metal")),
                    )
                )
            )
        )
        coEvery { service.fetchAlbums(limit) } returns expectedDto

        val result = dataSource.fetchAlbums(limit)

        assertEquals(DataResult.Success(expectedDto), result)
    }

    @Test
    fun `Given limit When fetchAlbums throws exception Then returns Failure`() = runBlocking {
        val limit = 10
        val exception = RuntimeException("Network error")
        coEvery { service.fetchAlbums(limit) } throws exception

        val result = dataSource.fetchAlbums(limit)

        assert(result is DataResult.Failure)
        assertEquals(exception, (result as DataResult.Failure).error)
    }
}

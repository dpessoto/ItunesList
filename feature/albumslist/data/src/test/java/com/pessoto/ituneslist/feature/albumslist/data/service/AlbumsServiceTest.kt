package com.pessoto.ituneslist.feature.albumslist.data.service

import com.pessoto.ituneslist.feature.albumslist.data.model.AlbumEntryDto
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedDto
import com.pessoto.ituneslist.feature.albumslist.data.model.FeedResponseDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdAttributesDto
import com.pessoto.ituneslist.feature.albumslist.data.model.IdDto
import com.pessoto.ituneslist.feature.albumslist.data.model.ImageDto
import com.pessoto.ituneslist.feature.albumslist.data.model.LabelDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class AlbumsServiceTest {

    private val service = mockk<AlbumsService>()

    @Test
    fun `Given limit When fetchAlbums Then returns expected DTO`() = runBlocking {
        val limit = 10
        val expected = FeedResponseDto(
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
                        )
                    )
                )
            )
        )
        coEvery { service.fetchAlbums(limit) } returns expected

        val result = service.fetchAlbums(limit)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `Given limit When fetchAlbums throws exception Then error is propagated`() {
        runBlocking {
            val limit = 10
            coEvery { service.fetchAlbums(limit) } throws RuntimeException("Network error")

            Assert.assertThrows(RuntimeException::class.java) {
                runBlocking {
                    service.fetchAlbums(limit)
                }
            }
        }
    }
}
package com.pessoto.ituneslist.feature.albumslist.domain.usecase

import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import com.pessoto.ituneslist.feature.albumslist.domain.repository.AlbumsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class FetchAlbumsUseCaseImplTest {

    @RelaxedMockK
    private lateinit var repository: AlbumsRepository
    private lateinit var useCase: FetchAlbumsUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = FetchAlbumsUseCaseImpl(repository)
    }

    @Test
    fun `Given limit When invoke Then returns Albums`() = runBlocking {
        val limit = 10
        val albums = listOf(
            Album(id = "1", name = "Album 1", artist = "Artist 1", imageUrl = "url1"),
            Album(id = "2", name = "Album 2", artist = "Artist 2", imageUrl = "url2")
        )
        coEvery { repository.fetchAlbums(limit) } returns flowOf(albums)

        val result = useCase.invoke(limit).first()
        assertEquals(albums, result)
    }
}

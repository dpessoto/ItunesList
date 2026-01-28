package com.pessoto.ituneslist.feature.albumdetail.presentation.viewmodel

import app.cash.turbine.test
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumDetailViewModelTest {

    private lateinit var viewModel: AlbumDetailViewModel
    private val album = Album(
        id = "1",
        albumName = "Album 1",
        artist = "Artist 1",
        images = listOf("url1"),
        price = "$9.99",
        releaseDate = "23 Jan 2026",
        genre = "Heavy Metal"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `GIVEN album WHEN ViewModel is created THEN state contains album`() = runTest {
        viewModel = AlbumDetailViewModel(album)
        assertEquals(album, viewModel.currentState().album)
    }

    @Test
    fun `WHEN OnBackClicked is dispatched THEN should send NavigateToBack event`() = runTest {
        viewModel = AlbumDetailViewModel(album)

        viewModel.event.test {
            viewModel.dispatch(AlbumDetailViewEvent.OnBackClicked)
            val event = awaitItem()
            assertEquals(AlbumDetailEvent.NavigateToBack, event)
        }
    }
}

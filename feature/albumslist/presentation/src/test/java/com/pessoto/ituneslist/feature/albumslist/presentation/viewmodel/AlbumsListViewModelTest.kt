package com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel

import app.cash.turbine.test
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import com.pessoto.ituneslist.feature.albumslist.domain.usecase.FetchAlbumsUseCase
import com.pessoto.ituneslist.ui.organism.LoadingState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumsListViewModelTest {

    @RelaxedMockK
    private lateinit var fetchAlbumsUseCase: FetchAlbumsUseCase
    private lateinit var viewModel: AlbumsListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = AlbumsListViewModel(fetchAlbumsUseCase, UnconfinedTestDispatcher())
    }

    @Test
    fun `GIVEN albums WHEN OnInit THEN should update ViewState with albums`() = runTest {
        val albums = listOf(
            Album(id = "1", albumName = "Album 1", artist = "Artist 1", imageUrl = "url1"),
            Album(id = "2", albumName = "Album 2", artist = "Artist 2", imageUrl = "url2")
        )
        coEvery { fetchAlbumsUseCase(any()) } returns flowOf(albums)

        viewModel.viewState.test {
            viewModel.dispatch(AlbumsListViewEvent.OnInit)

            val loadingState = awaitItem()
            assertTrue(loadingState.loadingState == LoadingState.Loading)
            assertTrue(loadingState.albums.isEmpty())
            assertTrue(loadingState.errorArgument == null)

            val successState = awaitItem()
            assertTrue(successState.loadingState == LoadingState.Loaded)
            assertTrue(successState.errorArgument == null)
            assertTrue(successState.albums.size == 2)
            assertTrue(successState.albums[0].id == "1")
        }
    }

    @Test
    fun `GIVEN error WHEN OnInit THEN should update ViewState with generic error`() = runTest {
        coEvery { fetchAlbumsUseCase(any()) } returns flow { throw Exception("error") }

        viewModel.viewState.test {
            viewModel.dispatch(AlbumsListViewEvent.OnInit)

            val loadingState = awaitItem()
            assertTrue(loadingState.loadingState == LoadingState.Loading)
            assertTrue(loadingState.albums.isEmpty())
            assertTrue(loadingState.errorArgument == null)

            val errorState = awaitItem()
            assertTrue(errorState.loadingState == LoadingState.Loaded)
            assertTrue(errorState.errorArgument != null)
            assertTrue(errorState.albums.isEmpty())
        }
    }

    @Test
    fun `GIVEN IOException WHEN OnInit THEN should update ViewState with network error`() =
        runTest {
            coEvery { fetchAlbumsUseCase(any()) } returns flow { throw IOException("network") }

            viewModel.viewState.test {
                viewModel.dispatch(AlbumsListViewEvent.OnInit)

                val loadingState = awaitItem()
                assertTrue(loadingState.loadingState == LoadingState.Loading)
                assertTrue(loadingState.albums.isEmpty())
                assertTrue(loadingState.errorArgument == null)

                val errorState = awaitItem()
                assertTrue(errorState.loadingState == LoadingState.Loaded)
                assertTrue(errorState.errorArgument != null)
                assertTrue(errorState.albums.isEmpty())
            }
        }

    @Test
    fun `GIVEN OnItemClick WHEN album is clicked THEN should send NavigateToDetail event`() =
        runTest {
            val albums = listOf(
                Album(id = "1", albumName = "Album 1", artist = "Artist 1", imageUrl = "url1"),
                Album(id = "2", albumName = "Album 2", artist = "Artist 2", imageUrl = "url2")
            )
            coEvery { fetchAlbumsUseCase(any()) } returns flowOf(albums)

            viewModel.event.test {
                viewModel.dispatch(AlbumsListViewEvent.OnInit)
                viewModel.dispatch(AlbumsListViewEvent.OnItemClick)

                val event = awaitItem()
                assertTrue(event is AlbumsListEvent.NavigateToDetail)
            }
        }
}

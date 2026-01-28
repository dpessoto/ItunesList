package com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pessoto.ituneslist.core.presentation.StateHandler
import com.pessoto.ituneslist.core.presentation.StateHandlerDelegate
import com.pessoto.ituneslist.feature.albumslist.domain.usecase.FetchAlbumsUseCase
import com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel.AlbumsListViewEvent.OnInit
import com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel.AlbumsListViewEvent.OnItemClick
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.molecule.AlbumItemArguments
import com.pessoto.ituneslist.ui.molecule.ItunesListErrorArguments
import com.pessoto.ituneslist.ui.organism.LoadingState
import com.pessoto.ituneslist.ui.util.TextContentType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.IOException

internal const val ALBUM_LIMIT = 100

sealed class AlbumsListViewEvent {
    data object OnInit : AlbumsListViewEvent()
    data object OnItemClick : AlbumsListViewEvent()
}

data class AlbumsListViewState(
    val loadingState: LoadingState = LoadingState.Loaded,
    val errorArgument: ItunesListErrorArguments? = null,
    val albums: ImmutableList<AlbumItemArguments> = persistentListOf(),
)

sealed class AlbumsListEvent {
    data object NavigateToDetail : AlbumsListEvent()
}

class AlbumsListViewModel(
    private val fetchAlbumsUseCase: FetchAlbumsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(),
    StateHandler<AlbumsListViewState, AlbumsListEvent> by StateHandlerDelegate(
        initialState = AlbumsListViewState(loadingState = LoadingState.Loading)
    ) {

    init {
        setupStateHandler(viewModelScope)
    }

    fun dispatch(event: AlbumsListViewEvent) {
        when (event) {
            is OnInit -> {
                fetchAlbums()
            }
            is OnItemClick -> {
                sendEvent(AlbumsListEvent.NavigateToDetail)
            }
        }
    }

    private fun fetchAlbums() {
        viewModelScope.launch(dispatcher) {
            fetchAlbumsUseCase(ALBUM_LIMIT)
                .onStart {
                    changeState(
                        currentState().copy(
                            loadingState = LoadingState.Loading,
                            errorArgument = null
                        )
                    )
                }.catch {
                    handleError(it)
                }.collect { repositories ->
                    val albums = repositories.map { album ->
                        AlbumItemArguments(
                            id = album.id,
                            albumName = TextContentType.Text(album.albumName),
                            artist = TextContentType.Text(album.artist),
                            albumImageUrl = album.imageUrl,
                            onClick = {
                                dispatch(OnItemClick)
                            },
                        )
                    }.toImmutableList()
                    changeState(
                        AlbumsListViewState(
                            errorArgument = null,
                            loadingState = LoadingState.Loaded,
                            albums = albums
                        )
                    )
                }
        }
    }

    private fun handleError(throwable: Throwable) {
        val description = when (throwable) {
            is IOException -> R.string.network_error_message
            else -> R.string.generic_message_error
        }

        val errorArgument = ItunesListErrorArguments(
            iconRes = R.drawable.ic_warning_generic_error,
            title = TextContentType.TextResource(R.string.generic_title_error),
            description = TextContentType.TextResource(description),
            buttonText = TextContentType.TextResource(R.string.generic_button_error),
            onClick = {
                dispatch(OnInit)
            }
        )

        changeState(
            currentState().copy(
                loadingState = LoadingState.Loaded,
                errorArgument = errorArgument
            )
        )
    }
}

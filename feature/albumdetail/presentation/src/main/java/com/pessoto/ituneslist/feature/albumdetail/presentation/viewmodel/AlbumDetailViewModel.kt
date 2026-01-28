package com.pessoto.ituneslist.feature.albumdetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pessoto.ituneslist.core.presentation.StateHandler
import com.pessoto.ituneslist.core.presentation.StateHandlerDelegate
import com.pessoto.ituneslist.feature.albumdetail.presentation.viewmodel.AlbumDetailViewEvent.OnBackClicked
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album


sealed class AlbumDetailViewEvent {
    data object OnBackClicked : AlbumDetailViewEvent()
}

data class AlbumDetailViewState(
    val album: Album? = null,
)

sealed class AlbumDetailEvent {
    data object NavigateToBack : AlbumDetailEvent()
}

class AlbumDetailViewModel(private val album: Album) : ViewModel(),
    StateHandler<AlbumDetailViewState, AlbumDetailEvent> by StateHandlerDelegate(
        initialState = AlbumDetailViewState(album = album)
    ) {

    init {
        setupStateHandler(viewModelScope)
    }

    fun dispatch(event: AlbumDetailViewEvent) {
        when (event) {
            is OnBackClicked -> {
                sendEvent(AlbumDetailEvent.NavigateToBack)
            }
        }
    }
}

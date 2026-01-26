package com.pessoto.ituneslist.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StateHandlerDelegate<ViewState, Event>(
    initialState: ViewState
) : StateHandler<ViewState, Event> {
    private lateinit var viewModelScope: CoroutineScope

    private val _viewState = MutableStateFlow(initialState)
    override val viewState: StateFlow<ViewState> get() = _viewState.asStateFlow()

    private val _event = MutableSharedFlow<Event>(replay = 0)
    override val event: SharedFlow<Event> get() = _event.asSharedFlow()

    override fun setupStateHandler(scope: CoroutineScope) {
        viewModelScope = scope
    }

    override fun currentState(): ViewState = _viewState.value

    override fun changeState(state: ViewState) {
        _viewState.value = state
    }

    override fun sendEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }
}

interface StateHandler<ViewState, Event> {
    val viewState: StateFlow<ViewState>
    val event: SharedFlow<Event>

    fun setupStateHandler(scope: CoroutineScope)
    fun changeState(state: ViewState)
    fun sendEvent(event: Event)
    fun currentState(): ViewState
}
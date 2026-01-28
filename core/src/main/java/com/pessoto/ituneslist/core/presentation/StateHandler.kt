package com.pessoto.ituneslist.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StateHandlerDelegate<ViewState, Event>(
    private val initialState: ViewState
) : StateHandler<ViewState, Event> {
    private lateinit var viewModelScope: CoroutineScope

    private var state = initialState

    private val _viewState: MutableSharedFlow<ViewState> = MutableSharedFlow<ViewState>().also {
        it.tryEmit(initialState)
    }
    override val viewState: SharedFlow<ViewState> get() = _viewState.asSharedFlow()

    private val _event = MutableSharedFlow<Event>(replay = 0)
    override val event: SharedFlow<Event> get() = _event.asSharedFlow()

    override fun setupStateHandler(scope: CoroutineScope) {
        viewModelScope = scope
        viewModelScope.launch {
            _viewState.collect {
                state = it
            }
        }
    }

    override fun currentState() = state

    override fun changeState(state: ViewState) {
        viewModelScope.launch { _viewState.emit(state) }
    }

    override fun sendEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }
}

interface StateHandler<ViewState, Event> {
    val viewState: SharedFlow<ViewState>
    val event: SharedFlow<Event>

    fun setupStateHandler(scope: CoroutineScope)
    fun changeState(state: ViewState)
    fun sendEvent(event: Event)
    fun currentState(): ViewState
}
package com.example.branchchat.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.branchchat.utils.dispatcher.CoroutineDispatchers
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<VIEW_STATE, VIEW_ACTION>(
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    abstract fun initialState(): VIEW_STATE
    private val _action = BroadcastChannel<VIEW_ACTION>(Channel.BUFFERED)
    val action = _action.asFlow()

    private val _state = MutableStateFlow(initialState())
    val state get() = _state

    protected fun updateState(viewState: VIEW_STATE) {
        _state.value = viewState
    }

    protected fun sendViewEvent(action: VIEW_ACTION) {
        _action.offer(action)
    }

    fun currentViewState() = _state.value

    fun executeRequest(
        request: suspend () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                withContext(coroutineDispatchers.io()) {
                    request()
                }
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }
}
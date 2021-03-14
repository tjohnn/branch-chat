package com.example.branchchat.presentation.chatthreads

import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.presentation.base.ViewState

data class ChatThreadsViewState(
    val isLoading: Boolean,
    val chatThreads: List<MessageModel>
) : ViewState

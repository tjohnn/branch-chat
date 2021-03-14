package com.example.branchchat.presentation.messages

import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.presentation.base.ViewState

data class MessagesViewState(
    val isLoading: Boolean,
    val isSendingMessage: Boolean,
    val messages: List<MessageModel>
) : ViewState

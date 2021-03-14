package com.example.branchchat.presentation.chatthreads

import com.example.branchchat.presentation.base.ViewEvent
import com.example.branchchat.presentation.messages.MessagesViewEvent

sealed class ChatThreadsViewEvent : ViewEvent  {
    data class OpenMessagesScreen(val threadId: Int) : ChatThreadsViewEvent()
    object DisplayLoadErrorSnackBar : ChatThreadsViewEvent()
}
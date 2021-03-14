package com.example.branchchat.presentation.messages

import com.example.branchchat.presentation.base.ViewEvent

sealed class MessagesViewEvent : ViewEvent  {
    object DisplayLoadErrorSnackBar : MessagesViewEvent()
    object DisplayNewMessageErrorSnackBar : MessagesViewEvent()
    object OnMessageSent : MessagesViewEvent()
}
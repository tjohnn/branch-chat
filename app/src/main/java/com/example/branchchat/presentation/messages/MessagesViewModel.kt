package com.example.branchchat.presentation.messages

import androidx.hilt.lifecycle.ViewModelInject
import com.example.branchchat.data.model.CreateMessageRequestModel
import com.example.branchchat.data.repository.ChatRepository
import com.example.branchchat.presentation.base.BaseViewModel
import com.example.branchchat.utils.dispatcher.CoroutineDispatchers

class MessagesViewModel @ViewModelInject constructor(
    private val chatRepository: ChatRepository,
    coroutineDispatchers: CoroutineDispatchers
) : BaseViewModel<MessagesViewState, MessagesViewEvent>(
    coroutineDispatchers
) {

    override fun initialState() = MessagesViewState(
        isLoading = false,
        messages = emptyList(),
        isSendingMessage = false
    )

    fun onViewCreated(threadId: Int) {
        updateState(
            currentViewState().copy(isLoading = true)
        )
        executeRequest(
            request = {
                val chats = chatRepository.messages(threadId)
                updateState(
                    currentViewState().copy(
                        isLoading = false,
                        messages = chats
                    )
                )
            },
            onError = {
                it.printStackTrace()
                sendViewEvent(MessagesViewEvent.DisplayLoadErrorSnackBar)
                updateState(
                    currentViewState().copy(isLoading = false)
                )
            }
        )
    }

    fun sendNewMessage(threadId: Int, message: String) {
        if (message.isBlank()) return
        updateState(
            currentViewState().copy(isSendingMessage = true)
        )
        executeRequest(
            request = {
                val newChat = chatRepository.createMessage(
                    CreateMessageRequestModel(threadId, message)
                )
                val chats = currentViewState().messages
                updateState(
                    currentViewState().copy(
                        isSendingMessage = false,
                        messages = chats + newChat
                    )
                )
                sendViewEvent(MessagesViewEvent.OnMessageSent)
            },
            onError = {
                it.printStackTrace()
                sendViewEvent(MessagesViewEvent.DisplayNewMessageErrorSnackBar)
                updateState(
                    currentViewState().copy(isSendingMessage = false)
                )
            }
        )
    }

}
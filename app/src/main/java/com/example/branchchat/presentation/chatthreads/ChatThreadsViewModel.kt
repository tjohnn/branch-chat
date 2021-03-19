package com.example.branchchat.presentation.chatthreads

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.data.repository.ChatRepository
import com.example.branchchat.presentation.base.BaseViewModel
import com.example.branchchat.presentation.messages.MessagesViewEvent
import com.example.branchchat.presentation.messages.MessagesViewState
import com.example.branchchat.utils.dispatcher.CoroutineDispatchers

class ChatThreadsViewModel @ViewModelInject constructor(
    private val chatRepository: ChatRepository,
    coroutineDispatchers: CoroutineDispatchers
) : BaseViewModel<ChatThreadsViewState, ChatThreadsViewEvent>(
    coroutineDispatchers
) {

    override fun initialState() = ChatThreadsViewState(
        isLoading = false,
        chatThreads = emptyList()
    )

    fun onViewCreated() {
        updateState(
            currentViewState().copy(isLoading = true)
        )
        executeRequest(
            request = {
                val chats = chatRepository.chats()
                updateState(
                    currentViewState().copy(
                        isLoading = false,
                        chatThreads = chats
                    )
                )
            },
            onError = {
                it.printStackTrace()
                sendViewEvent(ChatThreadsViewEvent.DisplayLoadErrorSnackBar)
                updateState(
                    currentViewState().copy(isLoading = false)
                )
            }
        )
    }

    fun onChatItemClickedAction(message: MessageModel) {
        sendViewEvent(ChatThreadsViewEvent.OpenMessagesScreen(message.threadId))
    }
}
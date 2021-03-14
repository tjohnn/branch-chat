package com.example.branchchat.data.repository

import com.example.branchchat.data.datasource.chat.ChatLocalSource
import com.example.branchchat.data.datasource.chat.ChatRemoteSource
import com.example.branchchat.data.model.CreateMessageRequestModel
import com.example.branchchat.data.model.MessageModel

class ChatDataRepository(
    private val chatRemoteSource: ChatRemoteSource,
    private val chatLocalSource: ChatLocalSource,
) : ChatRepository {

    override suspend fun chats(): List<MessageModel> {
        val messages = chatRemoteSource.getAllMessages()
        chatLocalSource.saveMessages(messages)
        return chatLocalSource.getThreads()
    }

    override suspend fun messages(threadId: Int) = chatLocalSource.getThreadMessages(threadId)

    override suspend fun createMessage(
        request: CreateMessageRequestModel
    ) = chatRemoteSource.createMessage(request)
}

package com.example.branchchat.data.repository

import com.example.branchchat.data.model.CreateMessageRequestModel
import com.example.branchchat.data.model.MessageModel

interface ChatRepository {
    suspend fun chats(): List<MessageModel>
    suspend fun messages(threadId: Int): List<MessageModel>
    suspend fun createMessage(request: CreateMessageRequestModel): MessageModel
}

package com.example.branchchat.data.datasource.chat

import com.example.branchchat.data.model.CreateMessageRequestModel
import com.example.branchchat.data.model.MessageModel

interface ChatRemoteSource {
    suspend fun getAllMessages(): List<MessageModel>
    suspend fun createMessage(request: CreateMessageRequestModel): MessageModel
}
package com.example.branchchat.data.datasource.chat

import com.example.branchchat.data.model.MessageModel

interface ChatLocalSource {
    suspend fun getThreads(): List<MessageModel>
    suspend fun saveMessages(messages: List<MessageModel>)
    suspend fun getThreadMessages(threadId: Int): List<MessageModel>
}
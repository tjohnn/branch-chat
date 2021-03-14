package com.example.branchchat.data.datasource.chat

import com.example.branchchat.data.local.MessagesInMemoryDao
import com.example.branchchat.data.model.MessageModel

class ChatLocalDataSource(
    private val messagesInMemoryDao: MessagesInMemoryDao
) : ChatLocalSource {

    override suspend fun getThreads() = messagesInMemoryDao.threads()

    override suspend fun saveMessages(messages: List<MessageModel>) {
        messagesInMemoryDao.saveMessages(messages)
    }

    override suspend fun getThreadMessages(threadId: Int) = messagesInMemoryDao.threadMessages(threadId)
}
package com.example.branchchat.data.local

import com.example.branchchat.data.model.MessageModel

class MessagesInMemoryDao {
    private val threadMessages = mutableMapOf<Int, MutableList<MessageModel>>()

    fun saveMessages(messages: List<MessageModel>) {
        threadMessages.clear()
        messages.sortedBy { it.timeCreated }.forEach { message ->
            if (threadMessages.containsKey(message.threadId)) {
                threadMessages[message.threadId]?.add(message)
            } else {
                threadMessages[message.threadId] = mutableListOf(message)
            }
        }
    }

    fun threads() = threadMessages.values.map { it.last() }

    fun threadMessages(threadId: Int) = threadMessages[threadId]?.toList() ?: emptyList()
}
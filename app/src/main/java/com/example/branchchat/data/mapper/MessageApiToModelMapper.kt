package com.example.branchchat.data.mapper

import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.data.model.Sender
import com.example.branchchat.data.remote.model.MessageApiModel

class MessageApiToModelMapper : Mapper<MessageApiModel, MessageModel> {
    override fun map(input: MessageApiModel) = MessageModel(
        id = input.id,
        sender = if (input.agentId != null) Sender.Agent(input.agentId)
        else Sender.User(input.userId ?: 0),
        threadId = input.threadId,
        body = input.body,
        timeCreated = input.timestamp
    )
}
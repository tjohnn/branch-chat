package com.example.branchchat.data.datasource.chat

import com.example.branchchat.data.mapper.CreateMessageRequestModelToApiMapper
import com.example.branchchat.data.mapper.MessageApiToModelMapper
import com.example.branchchat.data.model.CreateMessageRequestModel
import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.data.remote.ApiService

class ChatRemoteDataSource(
    private val apiService: ApiService,
    private val messageApiToModelMapper: MessageApiToModelMapper,
    private val createMessageRequestModelToApiMapper: CreateMessageRequestModelToApiMapper,
) : ChatRemoteSource {

    override suspend fun getAllMessages() = apiService.getMessages().map { message ->
            messageApiToModelMapper.map(message)
        }

    override suspend fun createMessage(request: CreateMessageRequestModel): MessageModel {
        val message = apiService.createMessage(
            createMessageRequestModelToApiMapper.map(request)
        )
        return messageApiToModelMapper.map(message)
    }
}
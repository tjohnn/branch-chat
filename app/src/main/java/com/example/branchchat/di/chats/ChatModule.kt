package com.example.branchchat.di.chats

import com.example.branchchat.data.datasource.chat.ChatLocalDataSource
import com.example.branchchat.data.datasource.chat.ChatLocalSource
import com.example.branchchat.data.datasource.chat.ChatRemoteDataSource
import com.example.branchchat.data.datasource.chat.ChatRemoteSource
import com.example.branchchat.data.local.MessagesInMemoryDao
import com.example.branchchat.data.mapper.CreateMessageRequestModelToApiMapper
import com.example.branchchat.data.mapper.MessageApiToModelMapper
import com.example.branchchat.data.remote.ApiService
import com.example.branchchat.data.repository.ChatDataRepository
import com.example.branchchat.data.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class ChatModule {

    @Provides
    @Reusable
    fun providesCreateMessageRequestModelToApiMapper() = CreateMessageRequestModelToApiMapper()

    @Provides
    @Reusable
    fun providesMessagesInMemoryDao() = MessagesInMemoryDao()

    @Provides
    @Reusable
    fun providesMessageApiToModelMapper() = MessageApiToModelMapper()

    @Provides
    @Reusable
    fun providesChatLocalSource(
        messagesInMemoryDao: MessagesInMemoryDao
    ): ChatLocalSource = ChatLocalDataSource(messagesInMemoryDao)

    @Provides
    @Reusable
    fun providesAuthRemoteSource(
        createMessageRequestModelToApiMapper: CreateMessageRequestModelToApiMapper,
        messageApiToModelMapper: MessageApiToModelMapper,
        apiService: ApiService
    ): ChatRemoteSource = ChatRemoteDataSource(
        apiService,
        messageApiToModelMapper,
        createMessageRequestModelToApiMapper
    )

    @Provides
    @Reusable
    fun providesChatRepository(
        chatRemoteSource: ChatRemoteSource,
        chatLocalSource: ChatLocalSource,
    ): ChatRepository = ChatDataRepository(
        chatRemoteSource,
        chatLocalSource
    )
}

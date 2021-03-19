package com.example.branchchat.presentation.chatthreads

import com.example.branchchat.MainCoroutineRule
import com.example.branchchat.TestCoroutineDispatchers
import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.data.model.Sender
import com.example.branchchat.data.repository.ChatRepository
import com.example.branchchat.presentation.messages.MessagesViewModel
import com.example.branchchat.presentation.messages.MessagesViewState
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

private val MESSAGE_1 = MessageModel(Sender.User(1), 1, 1, "body", Date(1))
private val MESSAGE_2 = MessageModel(Sender.User(1), 2, 2, "body", Date(2))
private val MESSAGE_3 = MessageModel(Sender.User(1), 3, 3, "body", Date(3))
class ChatThreadsViewModelTest {

    private lateinit var classUnderTest: ChatThreadsViewModel

    private val chatRepository = mockk<ChatRepository>()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        classUnderTest = ChatThreadsViewModel(chatRepository, TestCoroutineDispatchers())
    }

    @Test
    fun `Given thread latest messages When onViewCreated Then updates ui states`() {
        // Given
        val messages = listOf(MESSAGE_1, MESSAGE_2, MESSAGE_3)
        coEvery { chatRepository.chats() } returns messages

        // When
        classUnderTest.onViewCreated()

        val expectedViewState = ChatThreadsViewState(
            isLoading = false,
            chatThreads = messages
        )

        // Then
        Assert.assertEquals(expectedViewState, classUnderTest.currentViewState())
    }
}
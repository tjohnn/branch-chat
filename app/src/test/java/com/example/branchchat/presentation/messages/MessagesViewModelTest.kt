package com.example.branchchat.presentation.messages

import com.example.branchchat.MainCoroutineRule
import com.example.branchchat.TestCoroutineDispatchers
import com.example.branchchat.data.model.CreateMessageRequestModel
import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.data.model.Sender
import com.example.branchchat.data.repository.ChatRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

private val MESSAGE_1 = MessageModel(Sender.User(1), 1, 1, "body", Date(1))
private val MESSAGE_2 = MessageModel(Sender.User(1), 2, 1, "body", Date(2))
private val MESSAGE_3 = MessageModel(Sender.User(1), 3, 1, "body", Date(3))
class MessagesViewModelTest {

    private lateinit var classUnderTest: MessagesViewModel

    private val chatRepository = mockk<ChatRepository>()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        classUnderTest = MessagesViewModel(chatRepository, TestCoroutineDispatchers())
    }

    @Test
    fun `Given thread messages When onViewCreated Then updates ui states`() {
        // Given
        val messages = listOf(MESSAGE_1, MESSAGE_2, MESSAGE_3)
        coEvery { chatRepository.messages(1) } returns messages

        // When
        classUnderTest.onViewCreated(1)

        val expectedViewState = MessagesViewState(
            isLoading = false,
            messages = messages,
            isSendingMessage = false
        )

        // Then
        assertEquals(expectedViewState, classUnderTest.currentViewState())
    }

    @Test
    fun `Given a new message request When sendNewMessage Then creates a new message and update state`() {
        // Given
        val request = CreateMessageRequestModel(MESSAGE_1.threadId, MESSAGE_1.body)
        coEvery { chatRepository.createMessage(request) } returns MESSAGE_1

        // When
        classUnderTest.sendNewMessage(MESSAGE_1.threadId, MESSAGE_1.body)


        // Then
        assertEquals(MESSAGE_1, classUnderTest.currentViewState().messages.last())
    }
}
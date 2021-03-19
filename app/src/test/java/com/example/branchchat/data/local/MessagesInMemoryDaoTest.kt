package com.example.branchchat.data.local

import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.data.model.Sender
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

private val MESSAGE_1 = MessageModel(Sender.User(1), 1, 1, "body", Date(1))
private val MESSAGE_2 = MessageModel(Sender.User(1), 2, 3, "body", Date(3))
private val MESSAGE_3 = MessageModel(Sender.User(1), 3, 2, "body", Date(2))
private val MESSAGE_4 = MessageModel(Sender.User(1), 4, 1, "body", Date(6))
private val MESSAGE_5 = MessageModel(Sender.User(1), 5, 2, "body", Date(7))
private val MESSAGE_6 = MessageModel(Sender.User(1), 6, 3, "body", Date(5))

class MessagesInMemoryDaoTest {

    private lateinit var classUnderTest: MessagesInMemoryDao

    @Before
    fun setUp() {
        classUnderTest = MessagesInMemoryDao()
    }

    @Test
    fun `Given list of messages When threads Then returns latest thread messages`() {
        // Given
        val messages = listOf(MESSAGE_1, MESSAGE_2, MESSAGE_3, MESSAGE_4, MESSAGE_5, MESSAGE_6)

        val expected = listOf(MESSAGE_4, MESSAGE_5, MESSAGE_6)

        // When
        classUnderTest.saveMessages(messages)

        // Then
        assertEquals(expected, classUnderTest.threads())
    }

    @Test
    fun `Given list of messages and threadId When threadMessage Then returns expected thread list`() {
        // Given
        val messages = listOf(MESSAGE_1, MESSAGE_2, MESSAGE_3, MESSAGE_4, MESSAGE_5, MESSAGE_6)

        val expected = listOf(MESSAGE_1, MESSAGE_4)

        // When
        classUnderTest.saveMessages(messages)

        // Then
        assertEquals(expected, classUnderTest.threadMessages(1))
    }
}
package com.example.branchchat.data.remote.model

import com.example.branchchat.data.serialization.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class MessageApiModel(
    val id: Int,
    @SerialName("thread_id")
    val threadId: Int,
    @SerialName("user_id")
    val userId: Int?,
    @SerialName("agent_id")
    val agentId: Int?,
    val body: String,
    @Serializable(with = DateSerializer::class)
    val timestamp: Date
)

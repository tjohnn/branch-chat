package com.example.branchchat.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageRequestApiModel(
    @SerialName("thread_id")
    val threadId: Int,
    val body: String
)

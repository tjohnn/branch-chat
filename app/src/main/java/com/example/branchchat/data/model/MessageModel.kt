package com.example.branchchat.data.model

import android.content.res.Resources
import com.example.branchchat.R
import java.util.Date

data class MessageModel(
    val sender: Sender,
    val id: Int,
    val threadId: Int,
    val body: String,
    val timeCreated: Date
)

sealed class Sender(open val senderId: Int) {
    abstract fun label(resources: Resources): String
    data class Agent(override val senderId: Int) : Sender(senderId) {
        override fun label(resources: Resources) = resources.getString(R.string.from_agent_x, senderId)
    }

    data class User(override val senderId: Int) : Sender(senderId) {
        override fun label(resources: Resources) = resources.getString(R.string.from_user_x, senderId)
    }
}

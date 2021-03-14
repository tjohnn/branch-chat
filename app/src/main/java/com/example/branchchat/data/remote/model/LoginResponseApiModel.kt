package com.example.branchchat.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseApiModel(
    @SerialName("auth_token")
    val authToken: String
)

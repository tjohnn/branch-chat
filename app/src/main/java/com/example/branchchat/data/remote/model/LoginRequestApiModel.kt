package com.example.branchchat.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestApiModel(
    val username: String,
    val password: String,
)

package com.example.branchchat.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiListResponseModel<T>(
    val success: Boolean,
    val data: List<T>
)

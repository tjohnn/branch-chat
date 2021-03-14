package com.example.branchchat.data.remote

import com.example.branchchat.data.remote.model.CreateMessageRequestApiModel
import com.example.branchchat.data.remote.model.LoginRequestApiModel
import com.example.branchchat.data.remote.model.LoginResponseApiModel
import com.example.branchchat.data.remote.model.MessageApiModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/messages")
    suspend fun getMessages(): List<MessageApiModel>

    @POST("api/messages")
    suspend fun createMessage(@Body request: CreateMessageRequestApiModel): MessageApiModel

    @POST("api/login")
    suspend fun login(@Body request: LoginRequestApiModel): LoginResponseApiModel
}
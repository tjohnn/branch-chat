package com.example.branchchat.data.repository

import com.example.branchchat.data.model.LoginRequestModel

interface AuthRepository {
    suspend fun signIn(request: LoginRequestModel)
}

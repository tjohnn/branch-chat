package com.example.branchchat.data.datasource.auth

import com.example.branchchat.data.model.LoginRequestModel
import com.example.branchchat.data.model.LoginResponseModel

interface AuthRemoteSource {
    suspend fun signIn(request: LoginRequestModel): LoginResponseModel
}
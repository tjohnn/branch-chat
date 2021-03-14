package com.example.branchchat.data.repository

import com.example.branchchat.data.datasource.auth.AuthLocalSource
import com.example.branchchat.data.datasource.auth.AuthRemoteSource
import com.example.branchchat.data.mapper.LoginRequestModelToApiMapper
import com.example.branchchat.data.model.LoginRequestModel

class AuthDataRepository(
    private val authLocalSource: AuthLocalSource,
    private val authRemoteSource: AuthRemoteSource
) : AuthRepository {
    override suspend fun signIn(request: LoginRequestModel) {
        val token = authRemoteSource.signIn(request).authToken
        authLocalSource.saveToken(token)
    }
}
package com.example.branchchat.data.datasource.auth

import com.example.branchchat.data.local.AppPreferences

class AuthLocalDataSource(
    private val appPreferences: AppPreferences,
) : AuthLocalSource {

    override suspend fun saveToken(token: String) {
        appPreferences.authToken = token
    }

    override suspend fun getToken() = appPreferences.authToken
}

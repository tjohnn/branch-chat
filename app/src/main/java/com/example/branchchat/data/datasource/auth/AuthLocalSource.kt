package com.example.branchchat.data.datasource.auth

interface AuthLocalSource {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
}
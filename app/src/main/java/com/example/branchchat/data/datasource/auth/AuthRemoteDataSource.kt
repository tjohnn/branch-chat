package com.example.branchchat.data.datasource.auth

import com.example.branchchat.data.mapper.LoginRequestModelToApiMapper
import com.example.branchchat.data.mapper.LoginResponseApiToModelMapper
import com.example.branchchat.data.model.LoginRequestModel
import com.example.branchchat.data.model.LoginResponseModel
import com.example.branchchat.data.remote.ApiService

class AuthRemoteDataSource(
    private val loginResponseApiToModelMapper: LoginResponseApiToModelMapper,
    private val loginRequestModelToApiMapper: LoginRequestModelToApiMapper,
    private val apiService: ApiService
) : AuthRemoteSource {
    override suspend fun signIn(request: LoginRequestModel): LoginResponseModel {
        val apiRequest = loginRequestModelToApiMapper.map(request)
        return loginResponseApiToModelMapper.map(apiService.login(apiRequest))
    }
}

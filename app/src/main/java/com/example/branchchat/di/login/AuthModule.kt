package com.example.branchchat.di.login

import com.example.branchchat.data.datasource.auth.AuthLocalDataSource
import com.example.branchchat.data.datasource.auth.AuthLocalSource
import com.example.branchchat.data.datasource.auth.AuthRemoteDataSource
import com.example.branchchat.data.datasource.auth.AuthRemoteSource
import com.example.branchchat.data.local.AppPreferences
import com.example.branchchat.data.mapper.LoginRequestModelToApiMapper
import com.example.branchchat.data.mapper.LoginResponseApiToModelMapper
import com.example.branchchat.data.remote.ApiService
import com.example.branchchat.data.repository.AuthDataRepository
import com.example.branchchat.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class AuthModule {

    @Provides
    @Reusable
    fun providesLoginRequestModelToApiMapper() = LoginRequestModelToApiMapper()

    @Provides
    @Reusable
    fun providesLoginResponseApiToModelMapper() = LoginResponseApiToModelMapper()

    @Provides
    @Reusable
    fun providesAuthLocalSource(
        appPreferences: AppPreferences
    ): AuthLocalSource = AuthLocalDataSource(appPreferences)

    @Provides
    @Reusable
    fun providesAuthRemoteSource(
        loginRequestModelToApiMapper: LoginRequestModelToApiMapper,
        loginResponseApiToModelMapper: LoginResponseApiToModelMapper,
        apiService: ApiService
    ): AuthRemoteSource = AuthRemoteDataSource(
        loginResponseApiToModelMapper,
        loginRequestModelToApiMapper,
        apiService
    )

    @Provides
    @Reusable
    fun providesAuthRepository(
        authLocalSource: AuthLocalSource,
        authRemoteSource: AuthRemoteSource,
    ): AuthRepository = AuthDataRepository(
        authLocalSource,
        authRemoteSource
    )
}
package com.example.branchchat.di

import android.util.Log
import com.example.branchchat.data.local.AppPreferences
import com.example.branchchat.data.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideApiService(
        okHttpClient: OkHttpClient,
        serializer: Json
    ): ApiService {
        val contentType = "application/json".toMediaType()
        val retrofitBuilder = Builder()
        retrofitBuilder.baseUrl("https://android-messaging.branch.co")
        retrofitBuilder.client(okHttpClient)
        retrofitBuilder.addConverterFactory(serializer.asConverterFactory(contentType))
        return retrofitBuilder.build().create(ApiService::class.java)
    }

    @Provides
    @Reusable
    internal fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideSerializer() = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    @Provides
    @Singleton
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("NetworkRequestLog: ", message)
                }
            }
        )
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    internal fun providesTokenInterceptor(preferences: AppPreferences): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val newRequest: Request
            val token = preferences.authToken
            Log.d("OKHTTP_TOKEN", "Auth token $token")
            newRequest = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("X-Branch-Auth-Token", token.orEmpty())
                .build()
            chain.proceed(newRequest)
        }
    }
}

package com.example.branchchat.di

import android.content.Context
import com.example.branchchat.data.local.AppPreferences
import com.example.branchchat.utils.dispatcher.CoroutineDispatchers
import com.tjohnn.westwingcampaigns.utils.dispatcher.DefaultCoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    @Reusable
    fun providesCoroutineDispatchers(): CoroutineDispatchers = DefaultCoroutineDispatchers()

    @Provides
    @Reusable
    fun providesAppPreferences(
        @ApplicationContext context: Context
    ) = AppPreferences(context)
}
package com.tjohnn.westwingcampaigns.utils.dispatcher

import com.example.branchchat.utils.dispatcher.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCoroutineDispatchers @Inject constructor(): CoroutineDispatchers {
    override fun io() = Dispatchers.IO

    override fun main() = Dispatchers.Main

    override fun computation() = Dispatchers.Default

}
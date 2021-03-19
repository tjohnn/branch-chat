package com.example.branchchat

import com.example.branchchat.utils.dispatcher.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestCoroutineDispatchers : CoroutineDispatchers {
    override fun io() = Dispatchers.Main

    override fun main() = Dispatchers.Main

    override fun computation() = Dispatchers.Main

}
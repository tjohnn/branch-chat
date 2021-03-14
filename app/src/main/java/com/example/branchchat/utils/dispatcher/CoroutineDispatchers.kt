package com.example.branchchat.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
}
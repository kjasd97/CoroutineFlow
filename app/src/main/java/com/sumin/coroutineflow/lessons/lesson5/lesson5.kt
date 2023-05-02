package com.sumin.coroutineflow.lessons.lesson5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

val coroutineScope = CoroutineScope(Dispatchers.IO)
suspend fun main() {

    val flow = MutableSharedFlow<Int>()

    coroutineScope.launch {
        flow.collect {
            println(it)
        }
    }

    coroutineScope.launch {
        flow.collect {
            println(it)
        }
    }

}

fun getFlow(): Flow<Int> = flow {
    repeat(100) {
        println("Emitted $it")
        emit(it)
        delay(1000)
    }

}
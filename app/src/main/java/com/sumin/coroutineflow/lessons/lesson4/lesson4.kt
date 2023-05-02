package com.sumin.coroutineflow.lessons.lesson4

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val coroutineScope = CoroutineScope(Dispatchers.IO)
suspend fun main() {

    val flow = getFlow()

     val job1= coroutineScope.launch {
        flow.collect {
            println(it)
        }
    }

    val job2= coroutineScope.launch {
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
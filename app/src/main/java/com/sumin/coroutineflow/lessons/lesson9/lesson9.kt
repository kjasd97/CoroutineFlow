package com.sumin.coroutineflow.lessons.lesson9

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

suspend fun main() {

    val scope = CoroutineScope(Dispatchers.Default)

    val flow = MutableStateFlow<Int>(0)

    val producer = scope.launch {
        delay(500)
        repeat(10) {
            println("Emitted $it")
            flow.emit(it)
            println("After emit $it")
            delay(200)
        }
    }

    val consumer = scope.launch {
        flow.collect {
            println("Collected $it")
            delay(100)
        }
    }

    producer.join()
    consumer.join()
}
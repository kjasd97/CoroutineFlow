package com.sumin.coroutineflow.lessons.lesson8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

suspend fun main(){

    val scope = CoroutineScope(Dispatchers.Default)

    val job = scope.launch {
        val flow : Flow<Int> = flow {
            repeat(10){
                println("Emitted $it")
                emit(it)
            }
        }
        flow.collect{
            println("Collected $it")
        }
    }

    job.join()
}
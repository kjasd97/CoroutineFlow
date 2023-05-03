package com.sumin.coroutineflow.lessons.lesson10

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {
    loadData()
        .map {
            State.Content(value = it) as State
        }
        .onStart {
            emit(State.Loading)
        }
        .catch {
            emit(State.Error)
        }
        .collect {
            when (it) {
                is State.Content -> {
                    println("Collected ${it.value}")
                }
                State.Error -> {
                println("Something went wrong")
                }
                State.Loading -> {
                    println("Loading")
                }
            }
        }
}

fun loadData(): Flow<Int> = flow {
    repeat(5) {
        delay(500)
        emit(it)
    }
    throw java.lang.RuntimeException("Exception from flow")

}

sealed class State {
    data class Content(val value: Int) : State()
    object Loading : State()
    object Error : State()
}
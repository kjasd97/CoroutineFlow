package com.sumin.coroutineflow.crypto_app

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository


    private val loadingFlow = MutableSharedFlow<State>()

    val state: Flow<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart { emit(State.Loading) }
        .mergeWith(loadingFlow)


    private fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }


    fun refreshList() {
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            repository.refreshList()
        }
    }


//    private fun loadData() {
//        viewModelScope.launch {
//            repository.getCurrencyList()
//                .filter {
//                    Log.d("tag", "filter" )
//                    it.isNotEmpty()
//
//                }
//                .map {
//                    Log.d("tag", "map" )
//                    State.Content(currencyList = it) as State
//                }
//                .onStart {
//                    Log.d("tag", "start" )
//                    emit(State.Loading)
//                }
//                .onEach {
//                    _state.value = it
//                }
//                .collect()
//
//        }
//    }

}



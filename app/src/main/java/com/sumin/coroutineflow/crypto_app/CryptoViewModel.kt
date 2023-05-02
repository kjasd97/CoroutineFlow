package com.sumin.coroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    val state = repository.getCurrencyList()
        .filter {
            Log.d("tag", "filter")
            it.isNotEmpty()
        }
        .map {
            Log.d("tag", "map")
            State.Content(currencyList = it) as State
        }
        .onStart {
            Log.d("tag", "start")
            emit(State.Loading)
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



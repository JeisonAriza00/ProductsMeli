package com.jjariza.productsmeli.utils

sealed class State {
    object Loading : State()
    object Empty : State()
    data class Failed(val failure: String) : State()
    data class Success(var data: Any) : State() {
        inline fun <reified T> responseTo() = data as T
    }
}
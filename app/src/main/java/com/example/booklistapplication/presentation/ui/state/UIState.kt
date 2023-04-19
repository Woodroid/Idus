package com.example.booklistapplication.presentation.ui.state

sealed class UIState<out T : Any> {
    data class Success<out T : Any>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
    object Loading : UIState<Nothing>()
}
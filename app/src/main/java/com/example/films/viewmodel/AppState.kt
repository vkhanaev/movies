package com.example.films.viewmodel

sealed class AppState {
    data class Success(val film: Any) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

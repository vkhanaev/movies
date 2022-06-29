package com.example.movies.viewmodel

import com.example.movies.domain.Movie

sealed class AppState {
    data class Success(val movie: Movie) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

package com.example.movies.viewmodel

import com.example.movies.domain.Movie

sealed class AppState {
    data class SuccessSingle(val movie: Movie) : AppState()
    data class SuccessMultiNowPlaying(val moviesData: List<Movie>) : AppState()
    data class SuccessMultiUpcoming(val moviesData: List<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

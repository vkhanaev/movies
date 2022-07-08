package com.example.movies.viewmodel

import com.example.movies.domain.Movie
import com.example.movies.model.MovieSection

sealed class AppState {
    data class SuccessSingle(val movie: Movie) : AppState()
    data class SuccessMulti(val moviesData: List<Movie>, val movieSection: MovieSection) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

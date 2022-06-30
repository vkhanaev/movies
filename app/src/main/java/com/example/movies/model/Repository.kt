package com.example.movies.model

import com.example.movies.domain.Movie

fun interface RepositorySingle {
    fun getMovie(movieSection: MovieSection): Movie
}

fun interface RepositoryMulti {
    fun getListMovies(movieSection: MovieSection): List<Movie>
}

// в каком RecyclerView находится фильм
sealed class MovieSection {
    object NowPlaying: MovieSection()
    object Upcoming: MovieSection()
}
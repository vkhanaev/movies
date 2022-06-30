package com.example.movies.model

import com.example.movies.domain.Movie
import com.example.movies.domain.getDefaultMovie
import com.example.movies.domain.getNowPlayingMovieList
import com.example.movies.domain.getUpcomingMovieList


class RepositoryLocalImpl: RepositorySingle, RepositoryMulti {
    override fun getMovie(movieSection: MovieSection): Movie {
        return getDefaultMovie()
    }

    override fun getListMovies(movieSection: MovieSection): List<Movie> {
        return when(movieSection) {
            is MovieSection.NowPlaying -> getNowPlayingMovieList()
            is MovieSection.Upcoming -> getUpcomingMovieList()
        }
    }
}
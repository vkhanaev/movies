package com.example.movies.model

import com.example.movies.domain.Movie
import com.example.movies.domain.getDefaultMovie
import com.example.movies.domain.getNowPlayingMovieList
import com.example.movies.domain.getUpcomingMovieList
import java.lang.Thread.sleep

class RepositoryRemoteImpl: RepositorySingle, RepositoryMulti {
    override fun getMovie(movieSection: MovieSection): Movie {
        Thread{
            sleep(300L)
        }.start()
        return getDefaultMovie()
    }

    override fun getListMovies(movieSection: MovieSection): List<Movie> {
        Thread{
            sleep(300L)
        }.start()
        return when(movieSection) {
            is MovieSection.NowPlaying -> getNowPlayingMovieList()
            is MovieSection.Upcoming -> getUpcomingMovieList()
        }
    }
}
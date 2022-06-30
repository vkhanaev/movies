package com.example.movies.ui.main.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.domain.getDefaultMovieList
import com.example.movies.domain.getNowPlayingMovieList
import com.example.movies.model.*
import com.example.movies.viewmodel.AppState
import java.lang.Thread.sleep

class MovieListViewModel(val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    private lateinit var repositorySingle: RepositorySingle
    private lateinit var repositoryMulti: RepositoryMulti

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveDataToObserve
    }

    fun getMovies() = getDataFromSource()

    private fun getDataFromSource() {
        // Перед отправкой запроса состояние меняется на Loading
        liveDataToObserve.value = AppState.Loading

        // TODO Нужно доработать, не все догружается, если нет sleep()
        Thread {
            sleep(2000L)
            liveDataToObserve.postValue(AppState.SuccessMultiNowPlaying(repositoryMulti.getListMovies(MovieSection.NowPlaying)))

            sleep(2000L)
            liveDataToObserve.postValue(AppState.SuccessMultiUpcoming(repositoryMulti.getListMovies(MovieSection.Upcoming)))
        }.start()
    }

    fun choiceRepository() {
        repositorySingle = if(isConnected()) {
            RepositoryRemoteImpl()
        }else {
            RepositoryLocalImpl()
        }
        repositoryMulti = RepositoryLocalImpl()
    }

    private fun isConnected():Boolean{
        return false
    }
}
package com.example.movies.ui.main.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.domain.Movie
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

    fun getMovies(movieSection: MovieSection) = getDataFromSource(movieSection)

    private fun getDataFromSource(movieSection: MovieSection) {
        // Перед отправкой запроса состояние меняется на Loading
        liveDataToObserve.value = AppState.Loading

        Thread {
            sleep(1000L)
            liveDataToObserve.postValue(AppState.SuccessMulti(repositoryMulti.getListMovies(movieSection), movieSection))
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
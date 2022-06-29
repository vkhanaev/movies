package com.example.movies.ui.main.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.domain.getDefaultMovie
import com.example.movies.domain.getDefaultMovieList
import com.example.movies.viewmodel.AppState
import java.lang.Thread.sleep

class MovieListViewModel(val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getMovies() = getDataFromSource()

    private fun getDataFromSource() {
        // Перед отправкой запроса состояние меняется на Loading
        liveDataToObserve.value = AppState.Loading

        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.SuccessMulti(getDefaultMovieList()))
        }.start()
    }
}
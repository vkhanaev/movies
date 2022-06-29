package com.example.movies.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.viewmodel.AppState
import java.lang.Thread.sleep

class MainViewModel(val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getFilms() = getDataFromSource()

    private fun getDataFromSource() {
        // Перед отправкой запроса состояние меняется на Loading
        liveDataToObserve.value = AppState.Loading

        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(Any()))
        }.start()
    }
}
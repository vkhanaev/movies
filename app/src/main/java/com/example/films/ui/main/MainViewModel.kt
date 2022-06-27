package com.example.films.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.viewmodel.AppState
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
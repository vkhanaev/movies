package com.example.films.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(val liveData: MutableLiveData<Any> = MutableLiveData()) : ViewModel() {


    fun getData(): LiveData<Any> {
        getDataFromSource()
        return liveData
    }

    private fun getDataFromSource() {
        Thread {
            sleep(1000)
            liveData.postValue(Any())
        }.start()
    }
}
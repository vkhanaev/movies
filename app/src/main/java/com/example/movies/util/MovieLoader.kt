package com.example.movies.util

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.movies.BuildConfig
import com.example.movies.model.dto.MovieDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MovieLoader(private val listener: MovieLoaderListener) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie() {
        try {
            val uri =
                //URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=${lon}")
                URL("https://api.kinopoisk.dev/movie?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&search=%D0%BF%D0%B0%D1%83%D0%BA&field=name&isStrict=true")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "token",
                        BuildConfig.KINOPOISK_TOKEN
                    )
                    urlConnection.readTimeout = 5000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    // преобразование ответа от сервера (JSON) в модель данных (MovieDTO)
                    Log.d("@@@", bufferedReader.toString())
                    val movieDTO: MovieDTO =
                        Gson().fromJson(getLines(bufferedReader),
                            MovieDTO::class.java)
                    handler.post { listener.onLoaded(movieDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface MovieLoaderListener {
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(throwable: Throwable)
    }

}
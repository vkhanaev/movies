package com.example.movies.domain

data class Movie(
    val name: String,
    val nameEnglish: String,
    val genre: String,  // жанр фильма
    val duration: Int,
    val raiting: Double,
    val budget: Long,
    val revenue: Long,
    val releaseDate: String,
    val description: String
)

fun getDefaultMovie() = Movie("Человек-паук: Через вселенные",
    "Spider-Man: Into the Spider-Verse (2018)",
    "боевик, приключения, мультфильм",
    117,
    8.4,
    900,
    375,
    "2018-12-06", "Мы всё знаем о Питере Паркере. Он спас город, влюбился, а потом спасал город снова и снова… Но все это – в нашем измерении. А что если в результате работы гигантского коллайдера откроется окно из одного измерения в другое? Найдется ли в нем свой Человек-паук? И как он будет выглядеть? Приготовьтесь к тому, что в разных вселенных могут быть разные Люди-пауки и однажды им придется собраться вместе для борьбы с почти непобедимым врагом"
)
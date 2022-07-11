package com.example.movies.model.dto

import com.example.movies.R

data class MovieDTO (val fact: FactDTO?)

data class FactDTO(
    val alternativeName: String?,
    val description: String?,
    val enName: String?,
    val movieLength: Int?,
    val name: String?,
    val shortDescription: String?,
    val type: String?,
    val year: Int?,
    val raiting: Int?,
    val budget: Int?,
    val revenue: Int?
)

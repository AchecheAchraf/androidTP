package com.example.myapplication

data class Serie(
    val id: Int,
    val name: String,
    val overview: String,
    val popularity: Double,
    val backdrop_path: String?,
    val poster_path: String?,
    val first_air_date: String,
    val vote_average: Double,
    val vote_count: Int
)
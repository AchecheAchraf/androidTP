package com.example.myapplication

data class Actors(
    val page: Int,
    val results: List<Actor>,
    val total_pages: Int,
    val total_results: Int
)
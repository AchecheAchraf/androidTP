package com.example.myapplication

data class Actor(
    val id: Int,
    val name: String,
    val profile_path: String?,
    val known_for: List<String>,
    val character: String
)
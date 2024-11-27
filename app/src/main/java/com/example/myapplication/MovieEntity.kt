package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    val fiche: Movie,
    @PrimaryKey val id: String,
    var isFavorite: Boolean = false // Mark whether the movie is a favorite

)

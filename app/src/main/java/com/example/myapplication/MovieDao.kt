package com.example.myapplication

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteMovie(id: String)
}

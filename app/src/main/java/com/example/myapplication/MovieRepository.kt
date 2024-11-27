package com.example.myapplication


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: TmdbAPI) {

    suspend fun getMovies(apiKey: String) =
        service.getFilms(apiKey).results


    suspend fun getActors(apiKey: String) =
        service.getActors(apiKey).results


    suspend fun getSeries(apiKey: String) =
        service.getSeries(apiKey).results


    suspend fun getMovieCredits(movieId: Int, apiKey: String) =
        service.getMovieCredits(movieId, apiKey).cast


    suspend fun getSerieCredits(serieId: Int, apiKey: String) =
        service.getSerieCredits(serieId, apiKey).cast

}

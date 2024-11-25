package com.example.myapplication


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: TmdbAPI) {

    suspend fun getMovies(apiKey: String) = withContext(Dispatchers.IO) {
        service.getFilms(apiKey).results
    }

    suspend fun getActors(apiKey: String) = withContext(Dispatchers.IO) {
        service.getActors(apiKey).results
    }

    suspend fun getSeries(apiKey: String) = withContext(Dispatchers.IO) {
        service.getSeries(apiKey).results
    }

    suspend fun getMovieCredits(movieId: Int, apiKey: String) = withContext(Dispatchers.IO) {
        service.getMovieCredits(movieId, apiKey).cast
    }

    suspend fun getSerieCredits(serieId: Int, apiKey: String) = withContext(Dispatchers.IO) {
        service.getSerieCredits(serieId, apiKey).cast
    }
}

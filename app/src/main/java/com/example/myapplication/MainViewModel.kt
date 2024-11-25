package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())

    val apikey= "474915450c136f48794281389330d269"
    val serieDetails = MutableStateFlow<Serie?>(null)


    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)



    fun searchMovies(){
        viewModelScope.launch{
            movies.value = service.getFilms(apikey).results
        }
    }

    fun filterMovies(query: String) {
        val filteredList = if (query.isEmpty()) {
            movies.value
        } else {
            movies.value.filter { it.title.contains(query, ignoreCase = true) }
        }
        movies.value = filteredList
    }

    // Function to fetch actors
    fun searchActors() {
        viewModelScope.launch {
            try {
                val actorResponse = service.getActors(apikey)
                actors.value = actorResponse.results  // Correctly accessing the results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Function to filter actors by name
    fun filterActors(query: String) {
        val filteredList = if (query.isEmpty()) {
            actors.value
        } else {
            actors.value.filter { it.name.contains(query, ignoreCase = true) }
        }
        actors.value = filteredList
    }

    fun searchSeries() {
        viewModelScope.launch {
            try {
                val seriesResponse = service.getSeries(apikey)
                series.value = seriesResponse.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Filter series by name
    fun filterSeries(query: String) {
        val filteredList = if (query.isEmpty()) {
            series.value
        } else {
            series.value.filter { it.name.contains(query, ignoreCase = true) }
        }
        series.value = filteredList
    }

    fun fetchMovieCredits(movieId: Int) {
        viewModelScope.launch {
            try {
                val credits = service.getMovieCredits(movieId, apikey)
                actors.value = credits.cast // Store only the cast (actors)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchSerieCredits(serieId: Int) {
        viewModelScope.launch {
            try {
                val credits = service.getSerieCredits(serieId, apikey)
                actors.value = credits.cast
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val serieDetails = MutableStateFlow<Serie?>(null)

    private val apiKey = "474915450c136f48794281389330d269"

    fun searchMovies() {
        viewModelScope.launch {
            try {
                movies.value = repository.getMovies(apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
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

    fun searchActors() {
        viewModelScope.launch {
            try {
                actors.value = repository.getActors(apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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
                series.value = repository.getSeries(apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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
                actors.value = repository.getMovieCredits(movieId, apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchSerieCredits(serieId: Int) {
        viewModelScope.launch {
            try {
                actors.value = repository.getSerieCredits(serieId, apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


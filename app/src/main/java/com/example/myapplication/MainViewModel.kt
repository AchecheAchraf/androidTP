package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MovieDao
import com.example.myapplication.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val movieDao: MovieDao // Injected MovieDao for Room operations
) : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val favoriteMovies = MutableStateFlow<List<MovieEntity>>(listOf()) // StateFlow for favorite movies
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val serieDetails = MutableStateFlow<Serie?>(null)


    private val apiKey = "474915450c136f48794281389330d269"

    // Fetch movies from the repository
    fun searchMovies() {
        viewModelScope.launch {
            try {
                movies.value = repository.getMovies(apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadFavoriteMovies() {
        viewModelScope.launch {
            try {
                favoriteMovies.value = movieDao.getFavoriteMovies() // Fetch favorite movies from DB
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleFavorite(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                movie.isFavorite = !movie.isFavorite // Toggle favorite status
                if (movie.isFavorite) {
                    movieDao.insertMovie(movie) // Insert or update movie as favorite
                } else {
                    movieDao.deleteMovie(movie.id) // Remove from favorites if not
                }
                loadFavoriteMovies() // Refresh favorite movies
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun addFavoriteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                movie.isFavorite = true // Set isFavorite to true
                movieDao.insertMovie(movie)
                loadFavoriteMovies() // Refresh favorite movies
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeFavoriteMovie(movieId: String) {
        viewModelScope.launch {
            try {
                movieDao.deleteMovie(movieId)
                loadFavoriteMovies() // Refresh favorite movies
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }




    // Filter movies locally
    fun filterMovies(query: String) {
        val filteredList = if (query.isEmpty()) {
            movies.value
        } else {
            movies.value.filter { it.title.contains(query, ignoreCase = true) }
        }
        movies.value = filteredList
    }

    // Fetch actors from the repository
    fun searchActors() {
        viewModelScope.launch {
            try {
                actors.value = repository.getActors(apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Filter actors locally
    fun filterActors(query: String) {
        val filteredList = if (query.isEmpty()) {
            actors.value
        } else {
            actors.value.filter { it.name.contains(query, ignoreCase = true) }
        }
        actors.value = filteredList
    }

    // Fetch series from the repository
    fun searchSeries() {
        viewModelScope.launch {
            try {
                series.value = repository.getSeries(apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Filter series locally
    fun filterSeries(query: String) {
        val filteredList = if (query.isEmpty()) {
            series.value
        } else {
            series.value.filter { it.name.contains(query, ignoreCase = true) }
        }
        series.value = filteredList
    }

    // Fetch movie credits from the repository
    fun fetchMovieCredits(movieId: Int) {
        viewModelScope.launch {
            try {
                actors.value = repository.getMovieCredits(movieId, apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Fetch series credits from the repository
    fun fetchSerieCredits(serieId: Int) {
        viewModelScope.launch {
            try {
                actors.value = repository.getSerieCredits(serieId, apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Add a movie to favorites
}

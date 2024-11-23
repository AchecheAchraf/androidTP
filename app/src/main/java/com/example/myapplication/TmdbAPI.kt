package com.example.myapplication

import Movies

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilms(
        @Query("api_key") apikey: String,
        @Query("language") language: String = "fr"
    ): Movies

    // Define the endpoint for fetching actors (assuming TMDb API provides such an endpoint)
    @GET("trending/person/week")
    suspend fun getActors(
        @Query("api_key") apikey: String
    ): Actors

    @GET("trending/tv/week")
    suspend fun getSeries(
        @Query("api_key") apikey: String,
        @Query("language") language: String = "fr"
    ): Series

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Credits

    @GET("tv/{tv_id}/credits")
    suspend fun getSerieCredits(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): Credits


}
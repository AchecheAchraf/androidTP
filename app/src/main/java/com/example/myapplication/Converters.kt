package com.example.myapplication

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    private val movieJsonAdapter = moshi.adapter(Movie::class.java)

    @TypeConverter
    fun fromString(value: String): Movie? {
        return movieJsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun toString(movie: Movie): String {
        return movieJsonAdapter.toJson(movie)
    }
}

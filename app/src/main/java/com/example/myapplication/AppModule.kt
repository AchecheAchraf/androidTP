package com.example.myapplication

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideTmdbAPI(retrofit: Retrofit): TmdbAPI {
        return retrofit.create(TmdbAPI::class.java)
    }

    @Provides
    fun provideMovieRepository(service: TmdbAPI): MovieRepository {
        return MovieRepository(service)
    }
}

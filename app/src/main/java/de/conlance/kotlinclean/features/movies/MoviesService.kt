package de.conlance.kotlinclean.features.movies

import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetailsEntity
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesService @Inject constructor(retrofit: Retrofit):MoviesApi {

    private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

    override fun movies() = moviesApi.movies()
    override fun movieDetails(movieId: Int) = moviesApi.movieDetails(movieId)
}
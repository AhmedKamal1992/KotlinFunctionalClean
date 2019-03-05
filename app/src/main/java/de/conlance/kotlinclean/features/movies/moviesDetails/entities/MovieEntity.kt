package de.conlance.kotlinclean.features.movies.moviesDetails.entities

import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie

data class MovieEntity(private val id: Int, private val poster: String) {
    fun toMovie() = Movie(id, poster)
}

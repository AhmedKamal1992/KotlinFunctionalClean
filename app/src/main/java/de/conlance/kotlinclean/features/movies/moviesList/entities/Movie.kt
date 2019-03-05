package de.conlance.kotlinclean.features.movies.moviesList.entities

import de.conlance.kotlinclean.core.extension.empty

data class Movie(val id: Int, val poster: String) {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}
package de.conlance.kotlinclean.features.movies.moviesDetails.views

import androidx.lifecycle.MutableLiveData
import de.conlance.kotlinclean.core.platform.BaseViewModel
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetails
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetailsView
import javax.inject.Inject

class MoviesDetailsViewModel @Inject constructor(private val movieDetailsImpl: MovieDetailsImpl): BaseViewModel() {

    var movieDetailsMutable:MutableLiveData<MovieDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) = movieDetailsImpl(MovieDetailsImpl.Params(movieId)) { it.either(::handleFailure, ::handleMovieDetails) }

    private fun handleMovieDetails(movie: MovieDetails) {
        this.movieDetailsMutable.value = MovieDetailsView(movie.id, movie.title, movie.poster,
            movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
    }
}
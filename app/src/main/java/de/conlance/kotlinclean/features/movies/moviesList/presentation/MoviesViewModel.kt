package de.conlance.kotlinclean.features.movies.moviesList.presentation

import androidx.lifecycle.MutableLiveData
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.core.platform.BaseViewModel
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieView
import de.conlance.kotlinclean.features.movies.moviesList.MoviesUseCaseImpl
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesUseCaseImpl: MoviesUseCaseImpl): BaseViewModel<MoviesUseCaseImpl>(moviesUseCaseImpl) {

    var moviesMutable: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = moviesUseCaseImpl(UseCase.None()) { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        this.moviesMutable.value = movies.map {
            MovieView(
                it.id,
                it.poster
            )
        }
    }
}
package de.conlance.kotlinclean.features.movies.moviesList.views

import androidx.lifecycle.MutableLiveData
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.core.platform.BaseViewModel
import de.conlance.kotlinclean.features.MovieView
import de.conlance.kotlinclean.features.movies.moviesList.GetMoviesUseCase
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase): BaseViewModel() {

    var moviesMutable: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = getMoviesUseCase(UseCase.None()) { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        this.moviesMutable.value = movies.map { MovieView(it.id, it.poster) }
    }
}
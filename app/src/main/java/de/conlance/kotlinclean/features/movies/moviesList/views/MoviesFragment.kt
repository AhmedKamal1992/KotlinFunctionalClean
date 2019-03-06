package de.conlance.kotlinclean.features.movies.moviesList.views

import android.os.Bundle
import androidx.lifecycle.Observer
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.extension.failure
import de.conlance.kotlinclean.core.extension.observe
import de.conlance.kotlinclean.core.extension.viewModel
import de.conlance.kotlinclean.core.navigation.AppNavigator
import de.conlance.kotlinclean.core.platform.BaseFragment
import de.conlance.kotlinclean.features.MovieView
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject lateinit var navigator: AppNavigator
    @Inject lateinit var moviesAdapter: MoviesListAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId(): Int = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(moviesMutable, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    private fun renderMoviesList(movies: List<MovieView>?) {

    }

    private fun handleFailure(failure: Failure?) {

    }
}

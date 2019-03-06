package de.conlance.kotlinclean.features.movies.moviesList.views

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.extension.*
import de.conlance.kotlinclean.core.navigation.AppNavigator
import de.conlance.kotlinclean.core.platform.BaseFragment
import de.conlance.kotlinclean.features.MovieView
import de.conlance.kotlinclean.features.movies.MovieFailure
import kotlinx.android.synthetic.main.fragment_movies.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
    }

    private fun initializeView() {
        movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movieList.adapter = moviesAdapter
        moviesAdapter.clickListener = { movie, navigationExtras ->
            navigator.showMovieDetails(activity!!, movie, navigationExtras) }
    }

    private fun loadMoviesList() {
        emptyView.invisible()
        movieList.visible()
        showProgress()
        moviesViewModel.loadMovies()
    }

    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when(failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.serverError -> renderFailure(R.string.failure_server_error)
            is MovieFailure.ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes msg: Int) {
        movieList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(msg, R.string.action_refresh, ::loadMoviesList)
    }
}

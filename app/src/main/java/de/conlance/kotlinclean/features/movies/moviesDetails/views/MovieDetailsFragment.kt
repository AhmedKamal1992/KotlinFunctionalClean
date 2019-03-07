package de.conlance.kotlinclean.features.movies.moviesDetails.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.animators.MovieDetailsAnimator
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.extension.*
import de.conlance.kotlinclean.core.platform.BaseFragment
import de.conlance.kotlinclean.features.movies.MovieFailure
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetailsView
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieView
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.row_movie.*
import javax.inject.Inject


class MovieDetailsFragment : BaseFragment(){
    override fun layoutId() = R.layout.fragment_movie_details

    companion object {
        private const val PARAM_MOVIE = "param_movie"

        fun newInstance(movie: MovieView): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MOVIE, movie)
            movieDetailsFragment.arguments = arguments
            return movieDetailsFragment
        }
    }

    @Inject lateinit var movieDetailsAnimator: MovieDetailsAnimator
    private lateinit var movieDetailsViewModel: MoviesDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }

        movieDetailsViewModel = viewModel(viewModelFactory) {
            observe(movieDetailsMutable, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if(firstTimeCreated(savedInstanceState)) {
            movieDetailsViewModel.loadMovieDetails((arguments?.get(PARAM_MOVIE) as MovieView).id)
        } else {
            movieDetailsAnimator.scaleDownView(moviePlay)
            movieDetailsAnimator.cancelTransition(moviePoster)
            moviePoster.loadFromUrl((arguments?.get(PARAM_MOVIE) as MovieView).poster)
        }
    }

    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.isVisible())
            movieDetailsAnimator.scaleDownView(moviePlay)
        else
            movieDetailsAnimator.cancelTransition(movie_poster)
    }

    private fun renderMovieDetails(movie: MovieDetailsView?) {

    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> { notify(R.string.failure_network_connection); close() }
            is Failure.ServerError -> { notify(R.string.failure_server_error); close() }
            is MovieFailure.NonExistantMovie -> { notify(R.string.failure_movie_non_existent); close() }
        }
    }
}

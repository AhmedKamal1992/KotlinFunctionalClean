package de.conlance.kotlinclean.features.movies.moviesDetails.views

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import de.conlance.kotlinclean.AndroidTest
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetails
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqualTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesDetailsViewModelTest: AndroidTest() {
    private lateinit var viewModel: MoviesDetailsViewModel

    private val movieDetails = MovieDetails(0, "IronMan", "poster", "summary",
        "cast", "director", 2018, "trailer") //Expected value

    @Mock private lateinit var getMovieDetails: GetMovieDetails

    @Before fun setup() {
        getMovieDetails = mock { onBlocking {run(any())}.thenReturn(Either.Right(movieDetails))}
        viewModel = MoviesDetailsViewModel(getMovieDetails)
    }

    @Test fun loadingMovieDetailsShouldUpdateLiveData() {
        viewModel.movieDetailsMutable.observeForever {
            with(it!!) {
                id shouldEqualTo 0
                title shouldEqualTo "IronMan"
                poster shouldEqualTo "poster"
                summary shouldEqualTo "summary"
                cast shouldEqualTo "cast"
                director shouldEqualTo "director"
                year shouldEqualTo 2018
                trailer shouldEqualTo "trailer"
            }
        }

        runBlocking { viewModel.loadMovieDetails(0) }
    }
}
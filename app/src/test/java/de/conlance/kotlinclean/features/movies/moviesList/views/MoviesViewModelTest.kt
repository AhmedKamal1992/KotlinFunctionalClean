package de.conlance.kotlinclean.features.movies.moviesList.views

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import de.conlance.kotlinclean.AndroidTest
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.features.movies.moviesList.GetMoviesUseCase
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesViewModelTest: AndroidTest() {
    private lateinit var moviesViewModel: MoviesViewModel

    @Mock private lateinit var getMovies: GetMoviesUseCase

    private val moviesList = listOf(Movie(0, "IronMan"), Movie(1, "Batman")) //Expected Output

    @Before
    fun setup() {
        getMovies = mock { onBlocking { run(any())} doReturn Either.Right(moviesList)}
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test
    fun loadingMoviesShouldUpdateLiveData() {
        moviesViewModel.moviesMutable.observeForever {
            it.size shouldEqual 2
            it[0].poster shouldEqualTo "IronMan"
            it[1].id shouldEqualTo 1
            it[1].poster shouldEqualTo "Batman"
        }
        runBlocking { moviesViewModel.loadMovies() }
    }
}
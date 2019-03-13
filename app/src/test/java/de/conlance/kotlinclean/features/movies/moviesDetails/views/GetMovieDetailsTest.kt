package de.conlance.kotlinclean.features.movies.moviesDetails.views

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import de.conlance.kotlinclean.UnitTest
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMovieDetailsTest: UnitTest() {
    private val MOVIE_ID = 2

    private lateinit var getMovieDetails: GetMovieDetails

    @Mock lateinit var movieRepository: MoviesRepository

    @Mock private lateinit var scope: CoroutineScope

    @Mock private lateinit var dispatcher: CoroutineDispatcher

    @Before fun setup() {
        getMovieDetails = GetMovieDetails(movieRepository, scope, dispatcher)
        given { movieRepository.movieDetails(MOVIE_ID) }.willReturn(Either.Right(MovieDetails.empty()))
    }

    @Test fun shouldGetDataFromRepository() {
        runBlocking { getMovieDetails.run(GetMovieDetails.Params(MOVIE_ID)) }

        verify(movieRepository).movieDetails(MOVIE_ID)
        verifyNoMoreInteractions(movieRepository)
    }
}
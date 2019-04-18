package de.conlance.kotlinclean.features.movies.moviesList

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import de.conlance.kotlinclean.UnitTest
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesUseCaseImplTest: UnitTest() {
    private lateinit var moviesImpl: MoviesUseCaseImpl

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var scope: CoroutineScope

    @Mock
    private lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        moviesImpl = MoviesUseCaseImpl(moviesRepository, scope, dispatcher)
        given { moviesRepository.movies() }.willReturn(Either.Right(listOf(Movie.empty())))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { moviesImpl.run(UseCase.None()) }

        verify(moviesRepository).movies()
        verifyNoMoreInteractions(moviesRepository)
    }
}
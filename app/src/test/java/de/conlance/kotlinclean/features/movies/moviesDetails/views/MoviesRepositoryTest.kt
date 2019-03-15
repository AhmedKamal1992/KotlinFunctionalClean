package de.conlance.kotlinclean.features.movies.moviesDetails.views

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.willReturn
import de.conlance.kotlinclean.UnitTest
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.extension.empty
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.platform.NetworkHandler
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.MoviesService
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetails
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetailsEntity
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieEntity
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response


class MoviesRepositoryTest: UnitTest() {

    private lateinit var networkRepository: MoviesRepository.Network
    @Mock private lateinit var networkHandler: NetworkHandler
    @Mock private lateinit var moviesService: MoviesService

    @Mock private lateinit var movieCall: Call<List<MovieEntity>>
    @Mock private lateinit var movieDetailsCall: Call<MovieDetailsEntity>
    @Mock private lateinit var movieResponse: Response<List<MovieEntity>>

    @Mock private lateinit var moveDetailsCall: Call<MovieDetailsEntity>
    @Mock private lateinit var movieDetailsResponse: Response<MovieDetailsEntity>

    @Before
    fun setup() {
        networkRepository = MoviesRepository.Network(networkHandler, moviesService)
    }

    @Test
    fun shouldReturnEmptyListByDefault() {
        given { networkHandler.isConnected }.willReturn(true)
        given { movieResponse.body() }.willReturn(null)
        given { movieResponse.isSuccessful }.willReturn(true)
        given { movieCall.execute() }.willReturn(movieResponse)

        val movies = networkRepository.movies()

        movies shouldEqual Either.Right(emptyList<Movie>())
        verify(moviesService.movies())
    }

    @Test
    fun shouldGetMoviesListFromService() {
        given { networkHandler.isConnected }.willReturn(true)
        given { movieResponse.body() }.willReturn(listOf(MovieEntity(1, "poster")))
        given { movieResponse.isSuccessful }.willReturn(true)
        given { movieCall.execute() }.willReturn(movieResponse)
        given { moviesService.movies() }.willReturn(movieCall)

        val movies = networkRepository.movies()
        movies shouldEqual Either.Right(listOf(Movie(1, "poster")))
        verify(moviesService).movies()
    }

    @Test fun moviesServiceShouldReturnNetworkFailureWhenNoConnection() {
        given { networkHandler.isConnected }.willReturn(false)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java }, {})
        verifyZeroInteractions(moviesService)
    }

    @Test fun moviesMoviesServiceShouldReturnNetworkFailureWhenUndefinedConnection() {
        given { networkHandler.isConnected }.willReturn(null)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java }, {})
        verifyZeroInteractions(moviesService)
    }

    @Test fun moviesmMoviesServiceShouldReturnServerErrorIfNoSuccessfulResponse() {
        given { networkHandler.isConnected }.willReturn(true)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf Failure.ServerError::class.java }, {})
    }

    @Test fun moviesRequestShouldCatchExceptions() {
        given { networkHandler.isConnected }.willReturn(true)

        val movies = networkRepository.movies()

        movies shouldBeInstanceOf Either::class.java
        movies.isLeft shouldEqual true
        movies.either({ failure -> failure shouldBeInstanceOf Failure.ServerError::class.java }, {})
    }

    @Test fun shouldReturnEmptyMovieDetailsByDefault() {
        given { networkHandler.isConnected }.willReturn(true)
        given { movieDetailsResponse.body() }.willReturn(null)
        given { movieDetailsResponse.isSuccessful }.willReturn(true)
        given { movieDetailsCall.execute() }.willReturn(movieDetailsResponse)
        given { moviesService.movieDetails(1) }.willReturn(movieDetailsCall)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldEqual Either.Right(MovieDetails.empty())
        verify(moviesService).movieDetails(1)
    }

    @Test fun shouldGetMovieDetailsFromMoviesService() {
        given { networkHandler.isConnected }.willReturn(true)
        given { movieDetailsResponse.body() }.willReturn(
            MovieDetailsEntity(8, "title", String.empty(), String.empty(),
                String.empty(), String.empty(), 0, String.empty()))
        given { movieDetailsResponse.isSuccessful }.willReturn(true)
        given { movieDetailsCall.execute() }.willReturn(movieDetailsResponse)
        given { moviesService.movieDetails(1) }.willReturn(movieDetailsCall)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldEqual Either.Right(
            MovieDetails(
                8, "title", String.empty(), String.empty(),
                String.empty(), String.empty(), 0, String.empty()
            )
        )
        verify(moviesService).movieDetails(1)
    }

    @Test fun movieDetailsMoviesServiceShouldReturnNetworkFailureWhenNoConnection() {
        given { networkHandler.isConnected }.willReturn(false)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java }, {})
        verifyZeroInteractions(moviesService)
    }

    @Test fun movieDetailsMoviesServiceShouldReturnNetworkFailureWhenUndefinedConnection() {
        given { networkHandler.isConnected }.willReturn(null)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java }, {})
        verifyZeroInteractions(moviesService)
    }

    @Test fun movieDetailsMoviesServiceShouldReturnServerErrorIfNoSuccessfulResponse() {
        given { networkHandler.isConnected }.willReturn(true)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf Failure.ServerError::class.java }, {})
    }

    @Test fun movieDetailsRequestShouldCatchExceptions() {
        given { networkHandler.isConnected }.willReturn(true)

        val movieDetails = networkRepository.movieDetails(1)

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf Failure.ServerError::class.java }, {})
    }
}
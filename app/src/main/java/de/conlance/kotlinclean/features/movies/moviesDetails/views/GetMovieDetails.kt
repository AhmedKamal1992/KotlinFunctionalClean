package de.conlance.kotlinclean.features.movies.moviesDetails.views

import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Named

class GetMovieDetails @Inject constructor(private val moviesRepository: MoviesRepository, @Named("UseCaseScope")
    scope: CoroutineScope, @Named("UseCaseDispatchers") dispatcher: CoroutineDispatcher) :UseCase<MovieDetails, Params>(scope, dispatcher) {

    override suspend fun run(params: Params): Either<Failure, MovieDetails> = moviesRepository.movieDetails(params.id)
}

class Params(val id:Int)
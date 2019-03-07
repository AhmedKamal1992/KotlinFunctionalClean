package de.conlance.kotlinclean.features.movies.moviesList

import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Named

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository,
                       @Named("useCaseScope") scope: CoroutineScope,@Named("UseCaseDispatchers")
                                           dispatcher: CoroutineDispatcher): UseCase<List<Movie>, UseCase.None>(scope, dispatcher) {

    override suspend fun run(params: None): Either<Failure, List<Movie>> = moviesRepository.movies()
}
package de.conlance.kotlinclean.features.movies.moviesList

import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository): UseCase<List<Movie>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<Movie>> = moviesRepository.movies()
}
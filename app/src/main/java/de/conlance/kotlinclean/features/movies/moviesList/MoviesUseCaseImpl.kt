package de.conlance.kotlinclean.features.movies.moviesList

import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.injection.rx.RunOn
import de.conlance.kotlinclean.core.injection.rx.SchedulerType
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import io.reactivex.Scheduler
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Named

class MoviesUseCaseImpl @Inject constructor(private val moviesRepository: MoviesRepository, @RunOn(SchedulerType.IO) val ioScheduler: Scheduler, @RunOn(
    SchedulerType.MAIN) val mainScheduler: Scheduler): UseCase<List<Movie>, UseCase.None>(ioScheduler, mainScheduler) {

    override fun run(params: None): Either<Failure, List<Movie>> = moviesRepository.movies()
}
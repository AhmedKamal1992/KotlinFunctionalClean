package de.conlance.kotlinclean.features.movies.moviesDetails.views

import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.injection.rx.RunOn
import de.conlance.kotlinclean.core.injection.rx.SchedulerType
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.features.movies.MoviesRepository
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieDetails
import io.reactivex.Scheduler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Named

class MovieDetailsUseCaseImpl @Inject constructor(private val moviesRepository: MoviesRepository,
      @RunOn(SchedulerType.IO) ioScheduler: Scheduler, @RunOn(SchedulerType.MAIN) mainScheduler: Scheduler)
    :UseCase<MovieDetails, MovieDetailsUseCaseImpl.Params>(ioScheduler, mainScheduler) {

    override fun run(params: Params): Either<Failure, MovieDetails> = moviesRepository.movieDetails(params.id)

    class Params(val id:Int)
}


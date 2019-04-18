package de.conlance.kotlinclean.features.movies.moviesDetails

import android.content.Context
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.core.interactor.UseCase
import de.conlance.kotlinclean.core.navigation.AppNavigator
import io.reactivex.Scheduler
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Named

class PlayMovie
@Inject constructor(private val context: Context, private val navigator: AppNavigator,
                    private val ioScheduler: Scheduler, private val mainScheduler: Scheduler) : UseCase<UseCase.None, PlayMovie.Params>(ioScheduler, mainScheduler) {

    override fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Either.Right(None())
    }


    data class Params(val url: String)
}

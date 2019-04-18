package de.conlance.kotlinclean.core.interactor

import android.util.Log
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import de.conlance.kotlinclean.features.movies.moviesList.entities.Movie
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.*

abstract class UseCase<Type, in Params>(private val ioScheduler: Scheduler, private val mainScheduler: Scheduler) where Type : Any {
    private val compositeDisposable = CompositeDisposable()
    abstract fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {

        compositeDisposable.add(Single.create<Either<Failure, Type>> {
            val result = run(params)
            when(result.isRight) {
                true -> it.onSuccess(result)
                false -> it.onError(Throwable("error"))
            }
            }.
            subscribeOn(ioScheduler).observeOn(mainScheduler).
            subscribeWith(object: DisposableSingleObserver<Either<Failure, Type>>() {
            override fun onSuccess(t: Either<Failure, Type>) {
                onResult(t)
            }

            override fun onError(e: Throwable) {
                Log.e("Error", "Error")
            }

        }))
    }

    class None
}
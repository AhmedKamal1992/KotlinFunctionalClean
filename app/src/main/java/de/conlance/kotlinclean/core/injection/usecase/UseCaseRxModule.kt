package de.conlance.kotlinclean.core.injection.usecase

import dagger.Module
import dagger.Provides
import de.conlance.kotlinclean.core.injection.rx.RunOn
import de.conlance.kotlinclean.core.injection.rx.SchedulerType
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class UseCaseRxModule {

    @Provides
    @RunOn(SchedulerType.IO)
    @Singleton
    internal fun provideIo(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @RunOn(SchedulerType.COMPUTATION)
    @Singleton
    internal fun provideComputation(): Scheduler {
        return Schedulers.computation()
    }

    @Provides
    @RunOn(SchedulerType.MAIN)
    internal fun provideUi(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
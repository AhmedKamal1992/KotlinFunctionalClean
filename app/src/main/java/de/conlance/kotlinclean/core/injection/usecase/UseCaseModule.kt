package de.conlance.kotlinclean.core.injection.usecase

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class UseCaseModule {
    @Named("useCaseScope")
    @Provides
    @Singleton
    fun providesCoroutinesScope(): CoroutineScope = object: CoroutineScope {
        private val job = Job()
        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.IO
    }

    @Named("UseCaseDispatchers")
    @Provides
    @Singleton
    fun provideCoroutinesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
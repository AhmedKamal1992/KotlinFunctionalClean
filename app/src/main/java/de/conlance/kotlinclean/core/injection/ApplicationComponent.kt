package de.conlance.kotlinclean.core.injection

import dagger.Component
import de.conlance.kotlinclean.AndroidApplication
import de.conlance.kotlinclean.core.injection.viewModel.ViewModelModule
import de.conlance.kotlinclean.features.movies.moviesList.views.MoviesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(moviesFragment: MoviesFragment)
}
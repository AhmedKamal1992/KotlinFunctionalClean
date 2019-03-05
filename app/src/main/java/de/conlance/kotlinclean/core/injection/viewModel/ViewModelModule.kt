package de.conlance.kotlinclean.core.injection.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.conlance.kotlinclean.features.movies.moviesList.views.MoviesViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindServiceViewModel(moviesViewModel: MoviesViewModel): ViewModel
}
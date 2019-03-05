package de.conlance.kotlinclean.core.injection

import dagger.Component
import de.conlance.kotlinclean.AndroidApplication
import de.conlance.kotlinclean.core.injection.viewModel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
}
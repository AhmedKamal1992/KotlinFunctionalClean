package de.conlance.kotlinclean.core.navigation

import android.content.Context
import android.view.View
import de.conlance.kotlinclean.features.login.Authenticator
import de.conlance.kotlinclean.features.login.LoginActivity
import de.conlance.kotlinclean.features.movies.MoviesActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigator @Inject constructor(private val authenticator: Authenticator) {
    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) {
        when(authenticator.userLoggedIn()) {
            true -> showMovies(context)
            false -> showLogin(context)
        }
    }

    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))
    class Extras(val transitionSharedElement: View)
}
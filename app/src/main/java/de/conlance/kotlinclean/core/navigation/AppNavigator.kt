package de.conlance.kotlinclean.core.navigation

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieView
import de.conlance.kotlinclean.features.login.Authenticator
import de.conlance.kotlinclean.features.login.LoginActivity
import de.conlance.kotlinclean.features.movies.moviesList.MoviesActivity
import de.conlance.kotlinclean.features.movies.moviesDetails.views.MovieDetailsActivity
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
    fun showMovieDetails(activity: FragmentActivity, movie: MovieView, navigationExtras: Extras) {
        val intent = MovieDetailsActivity.callingIntent(activity, movie)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    class Extras(val transitionSharedElement: View)
}
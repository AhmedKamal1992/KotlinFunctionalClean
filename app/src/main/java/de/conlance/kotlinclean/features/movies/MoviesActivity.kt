package de.conlance.kotlinclean.features.movies

import android.content.Context
import android.content.Intent
import de.conlance.kotlinclean.core.platform.BaseActivity
import de.conlance.kotlinclean.core.platform.BaseFragment
import de.conlance.kotlinclean.features.movies.moviesList.views.MoviesFragment

class MoviesActivity: BaseActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment(): BaseFragment = MoviesFragment()
}
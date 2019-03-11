package de.conlance.kotlinclean.features.movies.moviesDetails.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.platform.BaseActivity
import de.conlance.kotlinclean.core.platform.BaseFragment
import de.conlance.kotlinclean.features.movies.moviesDetails.entities.MovieView

class MovieDetailsActivity : BaseActivity() {

    override fun fragment() = MovieDetailsFragment.newInstance(intent.getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE))

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE = "de.conlance.INTENT_PARAM_MOVIE"

        fun callingIntent(context: Context, movie: MovieView): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movie)
            return intent
        }
    }
}

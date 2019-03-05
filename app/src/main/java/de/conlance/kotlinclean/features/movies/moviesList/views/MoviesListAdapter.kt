package de.conlance.kotlinclean.features.movies.moviesList.views

import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.extension.inflate
import de.conlance.kotlinclean.core.extension.loadFromUrl
import de.conlance.kotlinclean.core.navigation.AppNavigator
import de.conlance.kotlinclean.features.MovieView
import kotlinx.android.synthetic.main.row_movie.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MoviesListAdapter @Inject constructor(): RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (MovieView, AppNavigator.Extras) -> Unit = {_,_-> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.row_movie))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(collection[position], clickListener)

    override fun getItemCount(): Int = collection.size

    class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        fun bind(movieView: MovieView, clickListener: (MovieView, AppNavigator.Extras) -> Unit) {
            itemView.moviePoster.loadFromUrl(movieView.poster)
            itemView.setOnClickListener { clickListener(movieView, AppNavigator.Extras(itemView.moviePoster)) }
        }
    }
}
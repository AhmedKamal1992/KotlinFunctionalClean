package de.conlance.kotlinclean.features.movies.moviesDetails.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieView(val id: Int, val poster: String) : Parcelable
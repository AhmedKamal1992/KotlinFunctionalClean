package de.conlance.kotlinclean.features

import android.os.Parcel
import android.os.Parcelable
import de.conlance.kotlinclean.core.platform.KParcelable
import de.conlance.kotlinclean.core.platform.parcelableCreator

data class MovieView(val id: Int, val poster: String) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::MovieView)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(poster)
        }
    }
}
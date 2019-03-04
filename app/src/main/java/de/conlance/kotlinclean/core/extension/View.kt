package de.conlance.kotlinclean.core.extension

import android.view.View

fun View.cancelTransition() {
    transitionName = null
}

fun View.isVisible() = this.visibility == View.VISIBLE
fun View.visible() { this.visibility = View.VISIBLE }
fun View.invisible() { this.visibility = View.GONE }


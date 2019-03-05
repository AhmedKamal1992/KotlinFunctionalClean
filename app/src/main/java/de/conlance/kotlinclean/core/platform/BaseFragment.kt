package de.conlance.kotlinclean.core.platform

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract fun layoutId(): Int


}
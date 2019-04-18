package de.conlance.kotlinclean.core.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import de.conlance.kotlinclean.core.platform.BaseActivity
import de.conlance.kotlinclean.core.platform.BaseFragment
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.fragment_movie_details.*

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified T: ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun BaseFragment.close() = fragmentManager?.popBackStack()
fun <B: ViewDataBinding> BaseFragment.binding(container: ViewGroup):B =
    DataBindingUtil.inflate(layoutInflater, layoutId(), container, false)


val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer
val BaseFragment.appContext: Context get() = activity?.applicationContext!!
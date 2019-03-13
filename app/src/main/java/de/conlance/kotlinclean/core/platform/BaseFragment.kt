package de.conlance.kotlinclean.core.platform

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import de.conlance.kotlinclean.AndroidApplication
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.extension.appContext
import de.conlance.kotlinclean.core.extension.binding
import de.conlance.kotlinclean.core.extension.viewContainer
import de.conlance.kotlinclean.core.injection.ApplicationComponent
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseFragment: Fragment() {
    abstract fun layoutId(): Int
    lateinit var binding: ViewDataBinding

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }


    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(container!!)
        return binding.root
    }

    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) { if (this is BaseActivity) this.progress.visibility = viewStatus }

    internal fun notify(@StringRes message: Int) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        val snackbar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(actionText) { action.invoke() }
        snackbar.setActionTextColor(ContextCompat.getColor(appContext, R.color.colorTextPrimary))
        snackbar.show()
    }
}
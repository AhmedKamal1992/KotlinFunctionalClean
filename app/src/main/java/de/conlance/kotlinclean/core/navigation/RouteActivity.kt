package de.conlance.kotlinclean.core.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.conlance.kotlinclean.AndroidApplication
import de.conlance.kotlinclean.R
import de.conlance.kotlinclean.core.injection.ApplicationComponent
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    @Inject
    internal lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }
}

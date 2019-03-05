package de.conlance.kotlinclean

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import de.conlance.kotlinclean.core.injection.ApplicationComponent
import de.conlance.kotlinclean.core.injection.ApplicationModule
import de.conlance.kotlinclean.core.injection.DaggerApplicationComponent

class AndroidApplication: Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.builder().
            applicationModule(ApplicationModule(this))
            .build()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
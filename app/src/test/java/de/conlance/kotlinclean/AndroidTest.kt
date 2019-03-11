package de.conlance.kotlinclean

import android.app.Application
import android.content.Context
import de.conlance.kotlinclean.core.platform.BaseActivity
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,
    application = AndroidTest.ApplicationStub::class,
    sdk = [21])
abstract class AndroidTest {

    @Rule @JvmField val injectMocks = InjectMocksRule.create(this@AndroidTest)
    fun context(): Context = RuntimeEnvironment.application
    fun activityContext(): Context = mock(BaseActivity::class.java)

    internal class ApplicationStub : Application()
}
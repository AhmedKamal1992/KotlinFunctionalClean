package de.conlance.kotlinclean

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@Suppress("LeakingThis")
@RunWith(MockitoJUnitRunner::class)
abstract class UnitTest {
    @Rule @JvmField val injectMocks = InjectMocksRule.create(this@UnitTest)
}
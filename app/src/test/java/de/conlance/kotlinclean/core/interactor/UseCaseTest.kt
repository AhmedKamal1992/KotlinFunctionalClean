package de.conlance.kotlinclean.core.interactor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import de.conlance.kotlinclean.AndroidTest
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.functional.Either
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config

@Config(manifest=Config.NONE)
class UseCaseTest: AndroidTest() {
    private val TYPE_TEST = "test"
    private val TYPE_PARAM = "ParamTest"

    private lateinit var useCase: UseCase<MyType, MyParams>

    @Before
    fun setup() {
        useCase = mock { onBlocking { run(any())} doReturn Either.Right(MyType(TYPE_TEST)) }
    }

    @Test
    fun runningUseCaseShouldReturnEitherOfUseCaseType() {
        val params = MyParams(TYPE_PARAM)
        val result = runBlocking { useCase.run(params) }
        result shouldEqual Either.Right(MyType(TYPE_TEST))
    }

    @Test
    fun shouldReturnCorrectDataWhenExecutingUseCase() {
        var result: Either<Failure, MyType>? = null

        val params = MyParams(TYPE_PARAM)
        val onResult = { myResult: Either<Failure, MyType> -> result = myResult }

        whenever(runBlocking { useCase(params, onResult) })
            .then { result shouldEqual Either.Right(MyType(TYPE_TEST)) }
    }

    data class MyType(val name: String)
    data class MyParams(val name: String)
}
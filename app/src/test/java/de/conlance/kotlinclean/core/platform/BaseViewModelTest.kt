package de.conlance.kotlinclean.core.platform

import androidx.lifecycle.MutableLiveData
import de.conlance.kotlinclean.core.exception.Failure
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Assert.*
import org.junit.Test

class BaseViewModelTest {
    @Test
    fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        viewModel.handleError(Failure.NetworkConnection)

        val failure = viewModel.failure
        val error = viewModel.failure.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf Failure.NetworkConnection::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}
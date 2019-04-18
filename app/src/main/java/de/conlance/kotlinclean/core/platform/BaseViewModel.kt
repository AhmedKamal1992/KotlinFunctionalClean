package de.conlance.kotlinclean.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.conlance.kotlinclean.core.exception.Failure
import de.conlance.kotlinclean.core.interactor.UseCase

abstract class BaseViewModel<T>(private val useCase: T) : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    override fun onCleared() {
        super.onCleared()
        (useCase as UseCase<*, *>).onCleared()
    }
}
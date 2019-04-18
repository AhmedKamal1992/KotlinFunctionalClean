package de.conlance.kotlinclean.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.conlance.kotlinclean.core.exception.Failure
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
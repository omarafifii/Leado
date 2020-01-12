package com.leado.common.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leado.common.schedulers.BaseSchedulerProvider
import com.leado.common.viewstate.BaseViewState
import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


abstract class BaseViewModel(private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    protected  val _loading: MutableLiveData<Boolean> = MutableLiveData()
    protected  val _error: MutableLiveData<Throwable>  = MutableLiveData()

    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _error

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun add(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }

    fun <X> applySchedulers(): ObservableTransformer<X, X> {
        return ObservableTransformer { up ->
            up.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }

    fun <X> applySchedulersSingle(): SingleTransformer<X, X> {
        return SingleTransformer { up ->
            up.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }

    fun applySchedulersCompletable(): CompletableTransformer {
        return CompletableTransformer { up ->
            up.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }

    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }


}
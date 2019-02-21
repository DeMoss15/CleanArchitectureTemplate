package com.demoss.cat.base.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<Action, State> : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    // for view state changing
    protected val _states: MutableLiveData<State> = MutableLiveData()
    // accessible for view data
    val states: LiveData<State> get() = _states

    open fun subscribeToActions(action: Observable<Action>) {
        compositeDisposable.add(action.subscribe { dispatchAction(it) })
    }

    open fun release() {
        compositeDisposable.dispose()
    }

    protected abstract fun dispatchAction(action: Action)
}
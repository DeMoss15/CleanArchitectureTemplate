package com.demoss.cat.base.mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.reactivex.subjects.PublishSubject

interface BaseView<Action, State, VM : BaseViewModel<Action, State>> {

    // for sending commands to view model
    val actions: PublishSubject<Action>
    val viewModel: VM

    fun dispatchState(newStatus: State)
    fun subscribeToViewModel(lifecycleOwner: LifecycleOwner) {
        viewModel.subscribeToActions(actions)
        viewModel.states.observe(lifecycleOwner, Observer<State> { newState -> dispatchState(newState) })
    }
}
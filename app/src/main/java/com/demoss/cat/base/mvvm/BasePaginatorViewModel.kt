package com.demoss.cat.base.mvvm

import com.demoss.cat.util.pagination.ReactivePaginator
import com.demoss.cat.util.pagination.ReactivePaginatorViewState
import io.reactivex.Observable

abstract class BasePaginatorViewModel<ItemType> : BaseViewModel<BasePaginatorAction, ReactivePaginatorViewState>() {

    abstract val requestFabric: (Observable<Int>) -> Observable<List<ItemType>>

    protected val stateDispatcher: (Observable<ReactivePaginatorViewState>) -> Unit = {
        compositeDisposable.add(it.subscribe { nextState -> _states.value = nextState })
    }
    protected lateinit var paginator: ReactivePaginator<ItemType>

    override fun subscribeToActions(action: Observable<BasePaginatorAction>) {
        super.subscribeToActions(action)
        paginator = ReactivePaginator(requestFabric, stateDispatcher)
        action.doOnDispose { compositeDisposable.clear() }.subscribe()
    }

    override fun dispatchAction(action: BasePaginatorAction): Unit = when (action) {
        ACTION_RESTART -> paginator.restart()
        ACTION_ -> paginator.refresh()
        ACTION_LOAD_NEXT_PAGE -> paginator.loadNewPage()
    }

    override fun onCleared() {
        paginator.release()
        super.onCleared()
    }
}
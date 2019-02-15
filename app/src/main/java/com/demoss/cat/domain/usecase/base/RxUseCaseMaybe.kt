package com.demoss.cat.domain.usecase.base

import com.demoss.cat.util.setDefaultSchedulers
import io.reactivex.Maybe
import io.reactivex.observers.DisposableMaybeObserver

abstract class RxUseCaseMaybe<T, Params> : BaseRxUseCase<Params, Maybe<T>, DisposableMaybeObserver<T>>() {
    final override fun execute(observer: DisposableMaybeObserver<T>, params: Params) {
        addDisposable(
            buildUseCaseObservable(params)
                .setDefaultSchedulers()
                .subscribeWith(observer)
        )
    }
}
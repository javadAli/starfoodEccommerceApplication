package com.example.starfood.common

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class StarFoodSingleObserver<T>(private val compositeDisposable: CompositeDisposable) :
    SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    fun onError(e: String) {
       // EventBus.getDefault().post(StarfoodExceptionMapper.map(e))
        Timber.e(e)
    }
}
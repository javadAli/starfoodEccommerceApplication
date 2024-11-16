package com.example.starfood.common

import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

abstract class StarFoodCompletableObserver(val compositeDisposable: CompositeDisposable) :
    CompletableObserver {
    override fun onError(e: Throwable) {
       // EventBus.getDefault().post(StarfoodExceptionMapper.map(e))
        Timber.e(e)
    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }
}
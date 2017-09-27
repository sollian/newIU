package com.sollian.base.Utils

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * @author sollian on 2017/9/26.
 */
class RxBus {
    companion object {
        val instance = RxBus()
    }

    private val bus: Subject<Any> = PublishSubject.create<Any>().toSerialized()

    fun send(obj: Any) {
        bus.onNext(obj)
    }

    fun <T> getObservable(type: Class<T>) = bus.ofType(type)!!
}
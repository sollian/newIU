package com.sollian.base.inter

/**
 * @author sollian on 2017/9/22.
 */
interface Consumer<T> {
    fun accept(t: T?)
}
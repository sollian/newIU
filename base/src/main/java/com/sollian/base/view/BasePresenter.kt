package com.sollian.base.view

import android.os.Bundle

/**
 * @author sollian on 2017/9/26.
 */
open class BasePresenter<out T : IContext>(val page: T) {
    open fun onCreate(savedInstanceState: Bundle?) {
    }

    open fun onDestroy() {
    }
}
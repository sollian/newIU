package com.sollian.base.view

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * @author sollian on 2017/10/9.
 */
open class BaseFragment<T : BasePresenter<*>> : Fragment(),
        IContext {
    protected var presenter: T? = null

    init {
        presenter = initPresenter()
    }

    open fun initPresenter(): T? = null

    override fun getContext() = activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter?.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}
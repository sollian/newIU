package com.sollian.base.view

import android.app.ProgressDialog
import android.os.Bundle
import com.sollian.base.R
import com.sollian.base.Utils.BaseContext
import java.lang.ref.WeakReference

/**
 * @author sollian on 2017/9/26.
 */
open class BasePresenter<T : IContext>(page: T) {

    val ref = WeakReference<T>(page)
    val progressDialog by lazy {
        val progress = ProgressDialog(getContext()!!.getContext())
        progress.isIndeterminate = true
        progress.setMessage(BaseContext.context.getString(R.string.waiting))
        progress
    }

    fun getContext(): T? = ref.get()

    open fun onCreate(savedInstanceState: Bundle?) {
    }

    open fun onDestroy() {
    }
}
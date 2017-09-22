package com.sollian.base.Utils

import android.os.Handler
import android.os.Looper

/**
 * @author sollian on 2017/9/22.
 */
object ThreadUtil {
    val handler: Handler = Handler(Looper.getMainLooper())

    fun post(runnable: () -> Unit) {
        if (Thread.currentThread().id == Looper.getMainLooper().thread.id)
            runnable()
        else
            handler.post(object : Runnable {
                override fun run() {
                    runnable()
                }
            })
    }
}
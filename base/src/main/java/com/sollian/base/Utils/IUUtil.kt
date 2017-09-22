package com.sollian.base.Utils

import android.widget.Toast

/**
 * @author sollian on 2017/9/22.
 */
object IUUtil {
    fun toast(msg: String) {
        ThreadUtil.post {
            Toast.makeText(BaseContext.context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
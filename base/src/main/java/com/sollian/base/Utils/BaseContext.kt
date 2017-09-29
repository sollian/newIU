package com.sollian.base.Utils

import android.content.Context
import com.sollian.base.kotlinext.DelegatesExt

/**
 * @author sollian on 2017/9/22.
 */
class BaseContext {
    companion object {
        var context by DelegatesExt.notNullSingleValue<Context>()

        fun initContext(context: Context) {
            this.context = context
        }

        fun initWithPermissions() {
            DirUtil.initDirs()
        }
    }
}
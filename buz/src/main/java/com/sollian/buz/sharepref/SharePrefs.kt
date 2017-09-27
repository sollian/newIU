package com.sollian.buz.sharepref

import com.sollian.base.Utils.BaseContext
import com.sollian.base.kotlinext.DelegatesExt

/**
 * @author sollian on 2017/9/25.
 */
object SharePrefs {
    private val USER_NAME = "user_name"
    private val PASSWORD = "password"

    var name: String by DelegatesExt.preference(BaseContext.context, USER_NAME, "")
    var password: String by DelegatesExt.preference(BaseContext.context, PASSWORD, "")
}
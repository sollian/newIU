package com.sollian.buz.http

import com.sollian.base.http.AbsIUHttpManager

/**
 * @author sollian on 2017/9/22.
 */
class IUHttpManager : AbsIUHttpManager() {
    companion object {
        val instance: IUHttpManager by lazy {
            IUHttpManager()
        }
    }

    fun initUserInfo(name: String?, pwd: String?) {
        userName = name
        password = pwd
    }
}
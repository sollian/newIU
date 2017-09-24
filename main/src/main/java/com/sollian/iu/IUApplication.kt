package com.sollian.iu

import android.app.Application
import com.sollian.base.Utils.BaseContext

/**
 * @author sollian on 2017/9/22.
 */
class IUApplication : Application() {
    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        BaseContext.initContext(this)
    }
}
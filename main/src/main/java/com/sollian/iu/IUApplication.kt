package com.sollian.iu

import android.app.Application
import com.sollian.base.Utils.BaseContext
import com.sollian.base.Utils.DirUtil
import com.sollian.buz.http.IUHttpManager
import com.sollian.buz.sharepref.SharePrefs

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
        IUHttpManager.getInstance().initUserInfo(SharePrefs.name, SharePrefs.password)
    }
}
package com.sollian.iu.utils

import com.sollian.buz.sharepref.SharePrefs

/**
 * @author sollian on 2017/10/27.
 */
class SyncHelper {
    companion object {
        private val EXPIRED_TIME = 1000 * 60 * 60 * 24
    }

    fun trySync() {

    }

    private fun syncSection() {
        if(System.currentTimeMillis() - SharePrefs.sectionLastSyncTime < EXPIRED_TIME) {
            return
        }


    }
}
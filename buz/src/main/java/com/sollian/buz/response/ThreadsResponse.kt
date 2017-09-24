package com.sollian.buz.response

import com.sollian.buz.bean.Threads

/**
 * @author sollian on 2017/9/22.
 */
class ThreadsResponse(json: String?) : AbsResponse<Threads>(json) {
    override fun getObjClass() = Threads::class.java
}
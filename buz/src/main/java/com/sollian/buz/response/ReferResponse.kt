package com.sollian.buz.response

import com.sollian.buz.bean.Refer

/**
 * @author sollian on 2017/9/22.
 */
class ReferResponse(json: String?) : AbsResponse<Refer>(json) {
    override fun getObjClass() = Refer::class.java
}
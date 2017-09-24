package com.sollian.buz.response

import com.sollian.buz.bean.Vote

/**
 * @author sollian on 2017/9/22.
 */
class VoteResponse(json: String?) : AbsResponse<Vote>(json) {
    override fun getObjClass() = Vote::class.java
}
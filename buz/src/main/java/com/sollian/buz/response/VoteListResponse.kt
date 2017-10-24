package com.sollian.buz.response

import com.sollian.buz.bean.VoteList

/**
 * @author sollian on 2017/9/22.
 */
class VoteListResponse(json: String?) : AbsResponse<VoteList>(json) {
    override fun getObjClass() = VoteList::class.java
}
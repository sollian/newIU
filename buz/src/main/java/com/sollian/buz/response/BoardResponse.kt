package com.sollian.buz.response

import com.sollian.base.Utils.JsonUtil
import com.sollian.buz.bean.Article
import com.sollian.buz.bean.Board

/**
 * @author sollian on 2017/9/22.
 */
class BoardResponse(json: String?) : AbsResponse<Board>(json) {
    override fun getObjClass() = Board::class.java
}
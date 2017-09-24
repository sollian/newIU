package com.sollian.buz.response

import com.sollian.buz.bean.Search

/**
 * @author sollian on 2017/9/22.
 */
class SearchResponse(json: String?) : AbsResponse<Search>(json) {
    override fun getObjClass() = Search::class.java
}
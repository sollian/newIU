package com.sollian.buz.response

import com.sollian.buz.bean.Favorite

/**
 * @author sollian on 2017/9/22.
 */
class FavoriteResponse(json: String?) : AbsResponse<Favorite>(json) {
    override fun getObjClass() = Favorite::class.java
}
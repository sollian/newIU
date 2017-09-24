package com.sollian.buz.response

import com.sollian.buz.bean.Favorite
import com.sollian.buz.bean.User

/**
 * @author sollian on 2017/9/22.
 */
class FavoriteResponse(json: String?) : AbsResponse<Favorite>(json) {
    override fun getObjClass() = Favorite::class.java
}
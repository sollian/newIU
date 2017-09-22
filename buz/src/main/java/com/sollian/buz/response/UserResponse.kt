package com.sollian.buz.response

import com.sollian.base.Utils.JsonUtil
import com.sollian.buz.bean.User

/**
 * @author sollian on 2017/9/22.
 */
class UserResponse(json: String?) : AbsResponse(json) {
    var user: User? = null

    init {
        user = JsonUtil.parse(json, User::class.java)
        checkError(user)
    }
}
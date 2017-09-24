package com.sollian.buz.response

import com.sollian.buz.bean.Mail

/**
 * @author sollian on 2017/9/22.
 */
class MailResponse(json: String?) : AbsResponse<Mail>(json) {
    override fun getObjClass() = Mail::class.java
}
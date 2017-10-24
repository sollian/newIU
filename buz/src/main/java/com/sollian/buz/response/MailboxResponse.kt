package com.sollian.buz.response

import com.sollian.buz.bean.Mailbox

/**
 * @author sollian on 2017/9/22.
 */
class MailboxResponse(json: String?) : AbsResponse<Mailbox>(json) {
    override fun getObjClass() = Mailbox::class.java
}